package com.rodoyf.satonda.feature_app.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredNoteName(val value: String) : AddEditNoteEvent()
    data class ChangeNoteNameFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredDescription(val value: String) : AddEditNoteEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditNoteEvent()

    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
