package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _state = mutableStateOf(FavoriteNoteState())
    val state: State<FavoriteNoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getFavoriteNotes()
    }

    fun onEvent(event: FavoriteNoteEvent) {
        when (event) {

            is FavoriteNoteEvent.DeleteFavoriteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is FavoriteNoteEvent.OnFavoriteChangeNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        event.note.copy(
                            isFavorite = event.isFavorite,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }

            is FavoriteNoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    fun getFavoriteNotes() {
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