package com.rodoyf.satonda.feature_app.presentation.task

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.rodoyf.satonda.feature_app.domain.util.convertLongToTime
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.task.components.TaskItem
import com.rodoyf.satonda.feature_app.presentation.task.components.TaskSearchBar
import com.rodoyf.satonda.feature_app.presentation.task.components.TaskTopBar
import com.rodoyf.satonda.feature_app.presentation.util.CustomSnackbar
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd
import com.rodoyf.satonda.ui.theme.schoolbellFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    val state = viewModel.state.value
    val searchedText = state.searchQuery

    // sorting items on list
    val tasks = state.tasks
    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedDescending) }

    val sortedTasks = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> tasks.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> tasks.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> tasks.sortedByDescending { it.isDone }
        SortOrder.AlphabeticalAZ -> tasks.sortedBy { it.title[0] }
        SortOrder.AlphabeticalZA -> tasks.sortedByDescending { it.title[0] }
    }

    // for vibrate
    val vibrator = context.getSystemService(Vibrator::class.java)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getTasks()
            delay(300)
        }
    }

    Scaffold(
        topBar = {
            TaskTopBar(
                navController = navController
            )
        },
        bottomBar = {
            /// reklam
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
                    message = stringResource(R.string.task_deleted),
                    actionLabel = stringResource(R.string.undo),
                    actionIcon = Icons.Default.Undo,
                    durationInSeconds = 5,
                    actionOnClick = {
                        viewModel.onEvent(TaskEvent.RestoreTask)
                    }
                )
            }
        }
    ) {

        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
                contentPadding = PaddingValues(
                    top = 25.dp,
                    bottom = 70.dp      //50
                ),
            ) {

                // tasks title
                item {
                    Text(
                        text = stringResource(R.string.tasks),
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
                    TaskSearchBar(
                        ModifiedDescending = {
                            currentSortOrder = SortOrder.ModifiedDescending
                        },
                        ModifiedAscending = {
                            currentSortOrder = SortOrder.ModifiedAscending
                        },
                        SortIsDones = {
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

                val searchSortTasks = state.tasks.filter { task ->
                    task.title.contains(searchedText, ignoreCase = true)
                }

                if (searchedText.isEmpty()) {
                    items(
                        sortedTasks,
                        key = { task ->
                            task.id!!.toInt()
                        }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onDeleteClick = {
                                // vibration
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

                                viewModel.onEvent(TaskEvent.OnDeleteTaskClick(task))
                                scope.launch {

                                    val result = snackbarHostState.showSnackbar(
                                        message = "Task was deleted!",
                                        actionLabel = "UNDO"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(TaskEvent.RestoreTask)
                                    }
                                }
                            },
                            modifier = Modifier
                                .combinedClickable(
                                    onClick = {
                                        navController.navigate(
                                            Screen.AddEditTaskScreen.route +
                                                    "?taskId=${task.id}"
                                        )
                                    },
                                    onLongClick = {
                                        val isDoneForSharing = if (task.isDone) {
                                            "Yeah"
                                        } else {
                                            "Nope"
                                        }
                                        val parsedTime =
                                            convertLongToTime(task.timestamp)

                                        val sharingText = """
                                        Task: ${task.title}
                                        Description: ${task.description}
                                        Completed: $isDoneForSharing
                                        Date: ${parsedTime}
                                        """.trimIndent()
                                        val sendIntent: Intent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, sharingText)
                                            type = "text/plain"
                                        }
                                        val shareIntent = Intent.createChooser(sendIntent, null)

                                        context.startActivity(shareIntent)
                                    }
                                )
                                .animateItemPlacement(
                                    tween(durationMillis = 250)
                                ),
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                } else {
                    items(
                        searchSortTasks,
                        key = { task ->
                            task.id!!.toInt()
                        }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onDeleteClick = {
                                // vibration
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

                                viewModel.onEvent(TaskEvent.OnDeleteTaskClick(task))
                                scope.launch {

                                    val result = snackbarHostState.showSnackbar(
                                        message = "Task was deleted!",
                                        actionLabel = "UNDO"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(TaskEvent.RestoreTask)
                                    }
                                }
                            },
                            modifier = Modifier
                                .combinedClickable(
                                    onClick = {
                                        navController.navigate(
                                            Screen.AddEditTaskScreen.route +
                                                    "?taskId=${task.id}"
                                        )
                                    },
                                    onLongClick = {
                                        val parsedTime =
                                            convertLongToTime(task.timestamp)
                                        val isDoneForSharing = if (task.isDone) {
                                            "Yeah"
                                        } else {
                                            "Nope"
                                        }

                                        val sharingText = """
                                        Task: ${task.title}
                                        Description: ${task.description}
                                        Completed: $isDoneForSharing                                     
                                        Date: ${parsedTime}
                                        """.trimIndent()
                                        val sendIntent: Intent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, sharingText)
                                            type = "text/plain"
                                        }
                                        val shareIntent = Intent.createChooser(sendIntent, null)

                                        context.startActivity(shareIntent)
                                    }
                                )
                                .animateItemPlacement(
                                    tween(durationMillis = 250)
                                ),
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }
}