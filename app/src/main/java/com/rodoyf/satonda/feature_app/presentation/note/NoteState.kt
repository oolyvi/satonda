package com.rodoyf.satonda.feature_app.presentation.note

import com.rodoyf.satonda.feature_app.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val isOrderSectionVisible: Boolean = false,
    val isLoading: Boolean = false,
    var searchQuery: String = "",
)