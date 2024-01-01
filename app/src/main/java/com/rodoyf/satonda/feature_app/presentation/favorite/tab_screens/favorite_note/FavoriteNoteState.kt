package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_note

import com.rodoyf.satonda.feature_app.domain.model.Note

data class FavoriteNoteState(
    val notes: List<Note> = emptyList(),
)
