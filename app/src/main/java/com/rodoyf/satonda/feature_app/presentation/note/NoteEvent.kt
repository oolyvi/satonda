package com.rodoyf.satonda.feature_app.presentation.note

import com.rodoyf.satonda.feature_app.domain.model.Note

sealed class NoteEvent {
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()

    data class OnFavoriteChange(val note: Note, val isFavorite: Boolean) : NoteEvent()

    data class Search(val query: String) : NoteEvent()
    object ClearSearch : NoteEvent()
}