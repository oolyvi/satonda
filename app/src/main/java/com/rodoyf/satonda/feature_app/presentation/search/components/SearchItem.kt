package com.rodoyf.satonda.feature_app.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.ui.theme.ralewayFamily

@Composable
fun SearchItem(
    note: Note,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        // color box
        Column(
            modifier = Modifier
                .width(7.dp)
                .height(21.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(note.color))
        ) {
            Text(text = "")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = ralewayFamily,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }
    }
}