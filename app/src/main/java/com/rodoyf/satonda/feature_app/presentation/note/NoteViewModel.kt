package com.rodoyf.satonda.feature_app.presentation.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.NoteUseCases
import com.rodoyf.satonda.feature_app.presentation.util.empty_ui_states.EmptyUiStateNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    repository: NoteRepository,
) : ViewModel() {

    private val _noteScreenState = MutableLiveData<EmptyUiStateNote>()
    val noteScreenState: LiveData<EmptyUiStateNote>
        get() = _noteScreenState

    private val notesFlow: Flow<List<Note>> = repository.getNotes()

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        _noteScreenState.value = EmptyUiStateNote.Loading
        getNotes()
    }

    fun onEvent(event: NoteEvent) {
        when (event) {

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvent.OnFavoriteChange -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        event.note.copy(
                            isFavorite = event.isFavorite
                        )
                    )
                }
            }

            is NoteEvent.ClearSearch -> {
                _state.value = state.value.copy(searchQuery = "", notes = state.value.notes)
            }

            is NoteEvent.Search -> {
                val filteredNotes = state.value.notes.filter { book ->
                    book.title.contains(event.query, ignoreCase = true) ||
                            book.description.contains(event.query, ignoreCase = true)
                }
                _state.value = state.value.copy(searchQuery = event.query, notes = filteredNotes)
            }
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            notesFlow.collect { notes ->
                if (notes.isEmpty()) {
                    _noteScreenState.value = EmptyUiStateNote.Empty
                } else {
                    _noteScreenState.value = EmptyUiStateNote.Content(notes)
                }
            }
        }

        viewModelScope.launch {

            getNotesJob?.cancel()
            getNotesJob = noteUseCases.getNotes()
                .onEach { notes ->
                    _state.value = state.value.copy(
                        notes = notes
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}