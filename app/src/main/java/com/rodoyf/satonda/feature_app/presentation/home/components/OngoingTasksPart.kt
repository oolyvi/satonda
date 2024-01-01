package com.rodoyf.satonda.feature_app.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.util.SortOrder
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.home.components.empty_screens.EmptyOngoingScreenUi
import com.rodoyf.satonda.feature_app.presentation.task.TaskViewModel
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleInteractionSource
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.empty_ui_states.EmptyUiStateTask
import com.rodoyf.satonda.ui.theme.ralewayFamily

@Composable
fun OngoingTasksPart(
    navController: NavController,
) {

    val viewModel: TaskViewModel = hiltViewModel()

    val taskScreenState by viewModel.taskScreenState.observeAsState()

    val state = viewModel.state.value
    val tasks = state.tasks

    val currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedAscending) }

    val sortedTasks = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> tasks.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> tasks.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> tasks.sortedByDescending { it.isFavorite }
        SortOrder.AlphabeticalAZ -> tasks.sortedBy { it.title.length }
        SortOrder.AlphabeticalZA -> tasks.sortedByDescending { it.title.length }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, start = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // ongoing tasks title
            Text(
                text = stringResource(R.string.ongoing_tasks),
                fontFamily = ralewayFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // see all
            TextButton(
                onClick = {
                    navController.navigate(Screen.TaskScreen.route)
                },
                interactionSource = remember { NoRippleInteractionSource() },
            ) {
                Text(
                    text = stringResource(R.string.see_all),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        when (taskScreenState) {
            is EmptyUiStateTask.Loading -> {
                CircularProgressIndicator()
            }

            is EmptyUiStateTask.Content -> {
                val allTasksCompleted = sortedTasks.all { it.isDone }

                if (allTasksCompleted) {
                    EmptyOngoingScreenUi()
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(end = 10.dp, start = 10.dp)
                    ) {
                        items(
                            sortedTasks.take(10),
                            key = { task ->
                                task.id!!.toInt()
                            }
                        ) { task ->
                            HomeTaskItem(
                                task = task,
                                modifier = Modifier
                                    .clickableWithoutRipple(
                                        onClick = {
                                            navController.navigate(
                                                Screen.AddEditTaskScreen.route +
                                                        "?taskId=${task.id}"
                                            )
                                        }
                                    )
                            )
                        }
                    }
                }
            }

            is EmptyUiStateTask.Empty -> {
                EmptyOngoingScreenUi()
            }

            else -> {}
        }
    }
}