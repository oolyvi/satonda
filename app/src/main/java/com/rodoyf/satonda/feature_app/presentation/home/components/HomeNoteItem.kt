package com.rodoyf.satonda.feature_app.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.presentation.note.NoteEvent
import com.rodoyf.satonda.feature_app.presentation.note.NoteViewModel
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick2

@Composable
fun HomeNoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel(),
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        modifier = modifier
            .padding(10.dp)
            .width(220.dp)
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LimitedTextNote(
                    text = note.title,
                    maxCharacters = 60,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    singleLine = false,
                    maxLine = 2,
                    modifier = Modifier.fillMaxWidth(0.85f)
                )

                // favorite icon
                IconToggleButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick2(),
                    checked = note.isFavorite,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(NoteEvent.OnFavoriteChange(note, isChecked))
                    }
                ) {
                    Icon(
                        imageVector = if (note.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        },
                        tint = if (note.isFavorite) {
                            MaterialTheme.colorScheme.inverseOnSurface
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        contentDescription = stringResource(R.string.favorite_icon),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            LimitedTextNote(
                text = note.description,
                maxCharacters = 150,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                fontSize = 13.sp,
                singleLine = false,
                maxLine = 2,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun LimitedTextNote(
    modifier: Modifier,
    text: String,
    maxCharacters: Int,
    fontWeight: FontWeight,
    textAlign: TextAlign,
    singleLine: Boolean = false,
    maxLine: Int,
    fontSize: TextUnit,
) {
    val limitedText = if (text.length > maxCharacters) {
        text.substring(0, maxCharacters) + stringResource(R.string.threeDots)
    } else {
        text
    }

    Text(
        modifier = modifier,
        text = limitedText,
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = if (singleLine) 1 else maxLine,
        overflow = TextOverflow.Ellipsis,
    )
}