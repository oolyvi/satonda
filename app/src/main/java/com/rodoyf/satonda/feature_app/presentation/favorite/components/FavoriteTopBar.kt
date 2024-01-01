package com.rodoyf.satonda.feature_app.presentation.favorite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodoyf.satonda.R
import com.rodoyf.satonda.ui.theme.ralewayFamily

@Composable
fun FavoriteTopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 20.dp, top = 15.dp, end = 20.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.favorites),
            fontFamily = ralewayFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}