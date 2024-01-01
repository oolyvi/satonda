package com.rodoyf.satonda.feature_app.navigation.bottom_navigation

import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.navigation.Screen

sealed class BottomNavItems(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {

    object HomeScreen : BottomNavItems(
        route = Screen.HomeScreen.route,
        title = "Home",
        selectedIcon = R.drawable.home,
        unselectedIcon = R.drawable.home,
    )

    object SearchScreen : BottomNavItems(
        route = Screen.SearchScreen.route,
        title = "Search",
        selectedIcon = R.drawable.search,
        unselectedIcon = R.drawable.search,
    )

    object FavoriteScreen : BottomNavItems(
        route = Screen.FavoriteScreen.route,
        title = "Favorites",
        selectedIcon = R.drawable.love,
        unselectedIcon = R.drawable.love,
    )

    object MoreScreen : BottomNavItems(
        route = Screen.MoreScreen.route,
        title = "More",
        selectedIcon = R.drawable.more,
        unselectedIcon = R.drawable.more,
    )
}
