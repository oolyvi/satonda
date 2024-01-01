package com.rodoyf.satonda.feature_app.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.InvalidNoteException
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var note by mutableStateOf<Note?>(null)
        private set

    private val _titleState = mutableStateOf(AddEditNoteState())
    val titleState: State<AddEditNoteState> = _titleState

    private val _descriptionState = mutableStateOf(AddEditNoteState())
    val descriptionState: State<AddEditNoteState> = _descriptionState

    private val _noteColorState = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColorState: State<Int> = _noteColorState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _titleState.value = titleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _descriptionState.value = descriptionState.value.copy(
                            text = note.description,
                            isHintVisible = false
                        )
                        _noteColorState.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredNoteName -> {
                _titleState.value = titleState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeNoteNameFocus -> {
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            titleState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredDescription -> {
                _descriptionState.value = descriptionState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeDescriptionFocus -> {
                _descriptionState.value = descriptionState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            descriptionState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeColor -> {
                _noteColorState.value = event.color
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = titleState.value.text,
                                description = descriptionState.value.text,
                                timestamp = System.currentTimeMillis(),
                                isFavorite = note?.isFavorite ?: false,
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save the note"
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
        object SaveNote : UiEvent()
    }
}