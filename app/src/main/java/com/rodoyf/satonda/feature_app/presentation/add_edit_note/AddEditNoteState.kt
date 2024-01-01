package com.rodoyf.satonda.feature_app.presentation.add_edit_note

data class AddEditNoteState(
    val text: String = "",
    val isHintVisible: Boolean = true,
    var isFavorite: Boolean = false,
)