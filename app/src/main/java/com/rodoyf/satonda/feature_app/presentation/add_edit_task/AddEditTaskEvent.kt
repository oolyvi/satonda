package com.rodoyf.satonda.feature_app.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent {
    data class EnteredTaskTitle(val value: String) : AddEditTaskEvent()
    data class ChangeTaskTitleFocus(val focusState: FocusState) : AddEditTaskEvent()
    data class EnteredDescription(val value: String) : AddEditTaskEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditTaskEvent()

    data class ChangeColor(val color: Int) : AddEditTaskEvent()
    object SaveTask : AddEditTaskEvent()
}
