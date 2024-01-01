package com.rodoyf.satonda.feature_app.presentation.search

import com.rodoyf.satonda.feature_app.domain.model.Note

data class SearchState(
    var notes: List<Note> = emptyList(),
    var searchQuery: String = "",
    var isLoading: Boolean = false,
)