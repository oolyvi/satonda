package com.rodoyf.satonda.feature_app.presentation.util.empty_ui_states

import com.rodoyf.satonda.feature_app.domain.model.Note

sealed class EmptyUiStateNote {
    object Loading : EmptyUiStateNote()
    data class Content(val notes: List<Note>) : EmptyUiStateNote()
    object Empty : EmptyUiStateNote()
}