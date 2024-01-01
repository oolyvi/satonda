package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_note

import com.rodoyf.satonda.feature_app.domain.model.Note

sealed class FavoriteNoteEvent {
    data class DeleteFavoriteNote(val note: Note) : FavoriteNoteEvent()
    object RestoreNote : FavoriteNoteEvent()

    data class OnFavoriteChangeNote(val note: Note, val isFavorite: Boolean) : FavoriteNoteEvent()
}