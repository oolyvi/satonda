package com.rodoyf.satonda.feature_app.presentation.add_edit_task

data class AddEditTaskState(
    val text: String = "",
    val isHintVisible: Boolean = true,
    var isDone: Boolean = false,
    var isFavorite: Boolean = false,
)
