package com.rodoyf.satonda.feature_app.presentation.add_edit_task

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.InvalidTaskException
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var task by mutableStateOf<Task?>(null)
        private set

    private val _taskTitle = mutableStateOf(AddEditTaskState())
    val taskTitle: State<AddEditTaskState> = _taskTitle

    private val _description = mutableStateOf(AddEditTaskState())
    val description: State<AddEditTaskState> = _description

    private val _taskColor = mutableStateOf(Task.taskColors.random().toArgb())
    val taskColor: State<Int> = _taskColor

    private val _isDone = mutableStateOf(AddEditTaskState())
    val isDone: State<AddEditTaskState> = _isDone

    private val _isFavorite = mutableStateOf(AddEditTaskState())
    val isFavorite: State<AddEditTaskState> = _isFavorite

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTask(taskId)?.also { task ->
                        currentTaskId = task.id

                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        _description.value = description.value.copy(
                            text = task.description ?: "",
                            isHintVisible = false
                        )
                        _taskColor.value = task.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.EnteredTaskTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditTaskEvent.ChangeTaskTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }

            is AddEditTaskEvent.EnteredDescription -> {
                _description.value = description.value.copy(
                    text = event.value
                )
            }

            is AddEditTaskEvent.ChangeDescriptionFocus -> {
                _description.value = description.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            description.value.text.isBlank()
                )
            }

            is AddEditTaskEvent.ChangeColor -> {
                _taskColor.value = event.color
            }

            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                description = description.value.text,
                                timestamp = System.currentTimeMillis(),
                                isDone = task?.isDone ?: false,
                                isFavorite = task?.isFavorite ?: false,
                                color = taskColor.value,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save the task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object PopBackStack : UiEvent()
        object SaveTask : UiEvent()
    }
}