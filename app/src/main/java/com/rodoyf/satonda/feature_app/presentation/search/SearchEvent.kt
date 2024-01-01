package com.rodoyf.satonda.feature_app.presentation.search

sealed class SearchEvent {
    data class Search(val query: String) : SearchEvent()
    object ClearSearch : SearchEvent()
}