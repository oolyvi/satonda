package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteNoteHeader(
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        FavoriteNoteSortingPartScreen(
            ModifiedDescending = {
                ModifiedDescending()
            },
            ModifiedAscending = {
                ModifiedAscending()
            },
            TitleAZ = {
                TitleAZ()
            },
            TitleZA = {
                TitleZA()
            },
        )
    }
}