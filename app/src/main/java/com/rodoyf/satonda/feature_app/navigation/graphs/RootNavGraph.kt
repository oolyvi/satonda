package com.rodoyf.satonda.feature_app.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodoyf.satonda.feature_app.navigation.MainNavigation

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {

        composable(route = Graph.HOME) {
            MainNavigation()
        }
    }
}