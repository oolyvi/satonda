package com.rodoyf.satonda.feature_app.domain.util

sealed class SortOrder() {

    object ModifiedDescending : SortOrder()
    object ModifiedAscending : SortOrder()
    object SortIsDones : SortOrder()
    object AlphabeticalAZ : SortOrder()
    object AlphabeticalZA : SortOrder()
}