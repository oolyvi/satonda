package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _state = mutableStateOf(FavoriteTaskState())
    val state: State<FavoriteTaskState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        getFavoriteTasks()
    }

    fun onEvent(event: FavoriteTaskEvent) {
        when (event) {

            is FavoriteTaskEvent.OnFavoriteChangeTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(
                        event.task.copy(
                            isFavorite = event.isFavorite,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }

            is FavoriteTaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    recentlyDeletedTask = event.task
                }
            }

            is FavoriteTaskEvent.OnDoneChangeTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(
                        event.task.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            is FavoriteTaskEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
        }
    }

    fun getFavoriteTasks() {
        viewModelScope.launch {

            getTasksJob?.cancel()
            getTasksJob = taskUseCases.getTasks()
                .onEach { tasks ->
                    _state.value = state.value.copy(
                        tasks = tasks
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}