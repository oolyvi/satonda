package com.rodoyf.satonda.feature_app.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.TaskUseCases
import com.rodoyf.satonda.feature_app.presentation.util.empty_ui_states.EmptyUiStateTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    repository: TaskRepository,
) : ViewModel() {

    private val _taskScreenState = MutableLiveData<EmptyUiStateTask>()
    val taskScreenState: LiveData<EmptyUiStateTask>
        get() = _taskScreenState

    private val tasksFlow: Flow<List<Task>> = repository.getTasks()

    private val _state = mutableStateOf(TaskState())
    val state: State<TaskState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        _taskScreenState.value = EmptyUiStateTask.Loading
        getTasks()
    }

    fun onEvent(event: TaskEvent) {
        when (event) {

            is TaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    recentlyDeletedTask = event.task
                }
            }

            is TaskEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }

            is TaskEvent.OnDoneChange -> {
                viewModelScope.launch {
                    taskUseCases.addTask(
                        event.task.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            is TaskEvent.OnFavoriteChange -> {
                viewModelScope.launch {
                    taskUseCases.addTask(
                        event.task.copy(
                            isFavorite = event.isFavorite
                        )
                    )
                }
            }

            is TaskEvent.ClearSearch -> {
                _state.value = state.value.copy(searchQuery = "", tasks = state.value.tasks)
            }

            is TaskEvent.Search -> {
                val filteredTasks = state.value.tasks.filter { task ->
                    task.title.contains(event.query, ignoreCase = true)
                }
                _state.value = state.value.copy(searchQuery = event.query, tasks = filteredTasks)
            }
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            tasksFlow.collect { tasks ->
                if (tasks.isEmpty()) {
                    _taskScreenState.value = EmptyUiStateTask.Empty
                } else {
                    _taskScreenState.value = EmptyUiStateTask.Content(tasks)
                }
            }
        }

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