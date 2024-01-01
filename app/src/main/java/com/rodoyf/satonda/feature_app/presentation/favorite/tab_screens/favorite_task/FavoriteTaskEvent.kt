package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task

import com.rodoyf.satonda.feature_app.domain.model.Task

sealed class FavoriteTaskEvent {
    data class OnDeleteTaskClick(val task: Task) : FavoriteTaskEvent()
    object RestoreTask : FavoriteTaskEvent()

    data class OnDoneChangeTask(val task: Task, val isDone: Boolean) : FavoriteTaskEvent()
    data class OnFavoriteChangeTask(val task: Task, val isFavorite: Boolean) : FavoriteTaskEvent()
}
