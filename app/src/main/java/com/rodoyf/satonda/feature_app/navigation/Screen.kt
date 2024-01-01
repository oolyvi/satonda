package com.rodoyf.satonda.feature_app.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen("home_screen")
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
    object TaskScreen : Screen("task_screen")
    object AddEditTaskScreen : Screen("add_edit_task_screen")
    object FavoriteScreen : Screen("favorites_screen")
    object SearchScreen : Screen("search_screen")
    object MoreScreen : Screen("more_screen")
    object PrivacyPolicyScreen : Screen("privacy_policy_screen")
    object TermsScreen : Screen("terms_screen")
}