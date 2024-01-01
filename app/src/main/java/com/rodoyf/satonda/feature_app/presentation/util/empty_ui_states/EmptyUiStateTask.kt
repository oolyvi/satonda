package com.rodoyf.satonda.feature_app.presentation.util.empty_ui_states

import com.rodoyf.satonda.feature_app.domain.model.Task

sealed class EmptyUiStateTask {
    object Loading : EmptyUiStateTask()
    data class Content(val tasks: List<Task>) : EmptyUiStateTask()
    object Empty : EmptyUiStateTask()
}