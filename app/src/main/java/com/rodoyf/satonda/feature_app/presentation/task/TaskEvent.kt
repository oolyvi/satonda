package com.rodoyf.satonda.feature_app.presentation.task

import com.rodoyf.satonda.feature_app.domain.model.Task

sealed class TaskEvent {
    data class OnDeleteTaskClick(val task: Task) : TaskEvent()
    data class OnDoneChange(val task: Task, val isDone: Boolean) : TaskEvent()
    data class OnFavoriteChange(val task: Task, val isFavorite: Boolean) : TaskEvent()

    object RestoreTask : TaskEvent()

    data class Search(val query: String) : TaskEvent()
    object ClearSearch : TaskEvent()
}
