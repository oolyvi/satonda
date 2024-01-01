package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task

import com.rodoyf.satonda.feature_app.domain.model.Task

data class FavoriteTaskState(
    val tasks: List<Task> = emptyList(),
)
