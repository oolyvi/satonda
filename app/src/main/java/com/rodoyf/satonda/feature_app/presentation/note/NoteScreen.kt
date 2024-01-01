package com.rodoyf.satonda.feature_app.presentation.note

import android.annotation.SuppressLint
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.util.SortOrder
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.note.components.NoteItem
import com.rodoyf.satonda.feature_app.presentation.note.components.NoteSearchBar
import com.rodoyf.satonda.feature_app.presentation.note.components.NoteTopBar
import com.rodoyf.satonda.feature_app.presentation.util.CustomSnackbar
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.ui.theme.schoolbellFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "ObsoleteSdkInt",
    "CoroutineCreationDuringComposition", "UnrememberedMutableState"
)
@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    val state = viewModel.state.value
    val searchedText = state.searchQuery

    // sorting items on list
    val notes = state.notes
    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedDescending) }

    val sortedNotes = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> notes.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> notes.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> notes.sortedByDescending { it.isFavorite }
        SortOrder.AlphabeticalAZ -> notes.sortedBy { it.title[0] }
        SortOrder.AlphabeticalZA -> notes.sortedByDescending { it.title[0] }
    }

    // for vibrate
    val vibrator = context.getSystemService(Vibrator::class.java)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getNotes()
            delay(300)
        }
    }

    Scaffold(
        topBar = {
            NoteTopBar(
                navController = navController
            )
        },
        bottomBar = {
            if (activity != null) {
                ShowBannerAdd()
            }
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            ) {
                CustomSnackbar(
                    snackbarHostState = snackbarHostState,
                    message = stringResource(R.string.note_deleted),
                    actionLabel = stringResource(R.string.undo),
                    actionIcon = Icons.Default.Undo,
                    durationInSeconds = 5,
                    actionOnClick = {
                        viewModel.onEvent(NoteEvent.RestoreNote)
                    }
                )
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(
                top = 25.dp,
                bottom = 70.dp    // 50
            ),
        ) {

            // notes title
            item {
                Text(
                    text = stringResource(R.string.notes),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = schoolbellFamily,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 30.dp, bottom = 15.dp)
                )
            }

            item {
                NoteSearchBar(
                    ModifiedDescending = {
                        currentSortOrder = SortOrder.ModifiedDescending
                    },
                    ModifiedAscending = {
                        currentSortOrder = SortOrder.ModifiedAscending
                    },
                    SortFavorites = {
                        currentSortOrder = SortOrder.SortIsDones
                    },
                    TitleAZ = {
                        currentSortOrder = SortOrder.AlphabeticalAZ
                    },
                    TitleZA = {
                        currentSortOrder = SortOrder.AlphabeticalZA
                    },
                )
            }


            val searchSortNotes = state.notes.filter { note ->
                note.title.contains(searchedText, ignoreCase = true) ||
                        note.description.contains(searchedText, ignoreCase = true)
            }

            if (searchedText.isEmpty()) {
                items(
                    sortedNotes,
                    key = { note ->
                        note.id!!.toInt()
                    }
                ) { note ->
                    NoteItem(
                        note = note,
                        onDeleteClick = {
                            val vibrationEffect1: VibrationEffect =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    VibrationEffect.createOneShot(
                                        50,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                } else {
                                    TODO("VERSION.SDK_INT < O")
                                }

                            vibrator.cancel()
                            vibrator.vibrate(vibrationEffect1)

                            viewModel.onEvent(NoteEvent.DeleteNote(note))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Note was deleted!",
                                    actionLabel = "UNDO"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NoteEvent.RestoreNote)
                                }
                            }
                        },
                        modifier = Modifier
                            .clickableWithoutRipple(
                                onClick = {
                                    navController.navigate(
                                        Screen.AddEditNoteScreen.route +
                                                "?noteId=${note.id}"
                                    )
                                },
                            )
                            .animateItemPlacement(
                                tween(durationMillis = 150)
                            ),
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            } else {
                items(
                    searchSortNotes,
                    key = { note ->
                        note.id!!.toInt()
                    }
                ) { note ->
                    NoteItem(
                        note = note,
                        onDeleteClick = {
                            val vibrationEffect1: VibrationEffect =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    VibrationEffect.createOneShot(
                                        50,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                } else {
                                    TODO("VERSION.SDK_INT < O")
                                }

                            vibrator.vibrate(vibrationEffect1)
                            vibrator.cancel()

                            viewModel.onEvent(NoteEvent.DeleteNote(note))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Note was deleted!",
                                    actionLabel = "UNDO"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NoteEvent.RestoreNote)
                                }
                            }
                        },
                        modifier = Modifier
                            .clickableWithoutRipple(
                                onClick = {
                                    navController.navigate(
                                        Screen.AddEditNoteScreen.route +
                                                "?noteId=${note.id}"
                                    )
                                },
                            )
                            .animateItemPlacement(
                                tween(durationMillis = 150)
                            ),
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}