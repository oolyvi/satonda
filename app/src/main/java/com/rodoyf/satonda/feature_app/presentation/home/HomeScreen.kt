package com.rodoyf.satonda.feature_app.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.presentation.home.components.CategoriesPart
import com.rodoyf.satonda.feature_app.presentation.home.components.OngoingTasksPart
import com.rodoyf.satonda.feature_app.presentation.home.components.QuoteAndIconPart
import com.rodoyf.satonda.feature_app.presentation.home.components.RecentNotePart
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}


    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Scaffold(
            modifier = Modifier
                .padding(bottom = 60.dp),
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
                contentPadding = PaddingValues(
                    top = 30.dp,
                    bottom = 80.dp
                ),
            ) {
                item {
                    QuoteAndIconPart()
                }

                // categories
                item {
                    CategoriesPart(
                        navController = navController
                    )
                }

                // recent notes
                item {
                    RecentNotePart(
                        navController = navController
                    )
                }

                // recent tasks
                item {
                    OngoingTasksPart(
                        navController = navController
                    )
                }
            }
        }
    }
}