package com.rodoyf.satonda.feature_app.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.navigation.bottom_navigation.BottomNavItems
import com.rodoyf.satonda.feature_app.presentation.add_edit_note.AddEditNoteScreen
import com.rodoyf.satonda.feature_app.presentation.add_edit_task.AddEditTaskScreen
import com.rodoyf.satonda.feature_app.presentation.favorite.FavoriteScreen
import com.rodoyf.satonda.feature_app.presentation.home.HomeScreen
import com.rodoyf.satonda.feature_app.presentation.more.MoreScreen
import com.rodoyf.satonda.feature_app.presentation.note.NoteScreen
import com.rodoyf.satonda.feature_app.presentation.privacy_policy.PrivacyPolicyScreen
import com.rodoyf.satonda.feature_app.presentation.search.SearchScreen
import com.rodoyf.satonda.feature_app.presentation.task.TaskScreen
import com.rodoyf.satonda.feature_app.presentation.terms.TermsScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun HomeNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItems.HomeScreen.route
    ) {

        composable(
            route = BottomNavItems.HomeScreen.route,
        ) {
            HomeScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.NotesScreen.route,
        ) {
            NoteScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.AddEditNoteScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }

        composable(
            route = Screen.TaskScreen.route,
        ) {
            TaskScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.AddEditTaskScreen.route +
                    "?taskId={taskId}",
            arguments = listOf(
                navArgument(
                    name = "taskId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "taskColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("taskColor") ?: -1
            AddEditTaskScreen(
                navController = navController,
                taskColor = color
            )
        }

        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(navController = navController)
        }

        composable(
            route = BottomNavItems.FavoriteScreen.route
        ) {
            FavoriteScreen(
                navController = navController,
            )
        }

        composable(
            route = BottomNavItems.MoreScreen.route
        ) {
            MoreScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.PrivacyPolicyScreen.route
        ) {
            PrivacyPolicyScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.TermsScreen.route
        ) {
            TermsScreen(
                navController = navController
            )
        }
    }
}
