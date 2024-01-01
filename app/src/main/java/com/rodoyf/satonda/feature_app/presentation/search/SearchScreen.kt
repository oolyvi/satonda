package com.rodoyf.satonda.feature_app.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.search.components.EmptySearchScreen
import com.rodoyf.satonda.feature_app.presentation.search.components.SearchItem
import com.rodoyf.satonda.feature_app.presentation.search.components.SearchTopBar
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    val state = viewModel.state.value
    val searchedText = state.searchQuery

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getNotes()
            delay(300)
        }
    }

    Scaffold(
        modifier = Modifier
            .padding(
                bottom = 60.dp
            ),
        topBar = {
            SearchTopBar()
        },
        bottomBar = {
            if (activity != null) {
                ShowBannerAdd()
            }
        }
    ) {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 55.dp)
                    .background(MaterialTheme.colorScheme.surface),
                contentPadding = PaddingValues(
                    bottom = 80.dp,
                    top = 30.dp
                )
            ) {

                if (searchedText.isNotEmpty()) {
                    val filteredNotes = state.notes.filter { note ->
                        note.title.contains(searchedText, ignoreCase = true) ||
                                note.description.contains(searchedText, ignoreCase = true)
                    }

                    items(
                        filteredNotes,
                        key = { note ->
                            note.id!!.toInt()
                        }
                    ) { note ->
                        SearchItem(
                            note = note,
                            modifier = Modifier
                                .clickableWithoutRipple {
                                    navController.navigate(
                                        Screen.AddEditNoteScreen.route +
                                                "?noteId=${note.id}"
                                    )
                                }
                                .padding(start = 10.dp, end = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        if (state.searchQuery.isEmpty()) {
            EmptySearchScreen()
        }
    }
}