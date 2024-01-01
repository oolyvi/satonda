package com.rodoyf.satonda.feature_app.presentation.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.presentation.favorite.components.FavoriteTopBar
import com.rodoyf.satonda.feature_app.presentation.favorite.components.TabScreen
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    Scaffold(
        modifier = Modifier
            .padding(
                bottom = 60.dp
            ),
        bottomBar = {
            if (activity != null) {
                ShowBannerAdd()
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FavoriteTopBar()

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .draggable(
                        state = viewModel.dragState.value!!,
                        orientation = Orientation.Horizontal,
                        onDragStarted = {

                        },
                        onDragStopped = {
                            viewModel.updateTabIndexBasedOnSwipe()
                        }
                    )
            ) {
                TabScreen(
                    navController = navController
                )
            }
        }
    }
}