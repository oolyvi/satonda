package com.rodoyf.satonda.feature_app.presentation.task

import com.rodoyf.satonda.feature_app.domain.model.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    var isDone: Boolean = false,
    var isFavorite: Boolean = false,
    var searchQuery: String = "",
)
