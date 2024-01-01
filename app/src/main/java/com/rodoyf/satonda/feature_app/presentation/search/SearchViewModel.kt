package com.rodoyf.satonda.feature_app.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {

            is SearchEvent.Search -> {
                val filteredNotes = state.value.notes.filter { book ->
                    book.title.contains(event.query, ignoreCase = true) ||
                            book.description.contains(event.query, ignoreCase = true)
                }
                _state.value = state.value.copy(searchQuery = event.query, notes = filteredNotes)
            }

            is SearchEvent.ClearSearch -> {
                _state.value = state.value.copy(searchQuery = "", notes = state.value.notes)
            }
        }
    }

    fun getNotes() {
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