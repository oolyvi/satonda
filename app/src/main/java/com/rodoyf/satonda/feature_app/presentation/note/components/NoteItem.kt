package com.rodoyf.satonda.feature_app.presentation.note.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.util.convertLongToTime
import com.rodoyf.satonda.feature_app.presentation.note.NoteEvent
import com.rodoyf.satonda.feature_app.presentation.note.NoteViewModel
import com.rodoyf.satonda.feature_app.presentation.util.DeleteConfirmationDialog
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick2

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
) {

    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val parsedTime = convertLongToTime(note.timestamp)

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
        ) {

            Text(
                text = note.title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = parsedTime,
                fontWeight = FontWeight.ExtraLight,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = note.description,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            // general row / fav, del, share
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // favorite icon
                IconToggleButton(
                    checked = note.isFavorite,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(NoteEvent.OnFavoriteChange(note, isChecked))
                    },
                    modifier = Modifier.pulsateClick2()
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
                        contentDescription = stringResource(id = R.string.favorite_icon),
                        modifier = Modifier.size(20.dp)
                    )
                }

                // share icon
                IconButton(
                    onClick = {
                        val sharingText = """
                            Note: ${note.title}
                            Description: ${note.description}
                            Date: $parsedTime                                               
                            """.trimIndent()
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, sharingText)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)

                        context.startActivity(shareIntent)
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.share),
                        contentDescription = stringResource(id = R.string.share_note),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(23.dp)
                    )
                }

                // delete icon
                IconButton(
                    onClick = {
                        deleteConfirmationRequired = true
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.bin),
                        contentDescription = stringResource(id = R.string.delete_note),
                        tint = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteClick = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {
                            deleteConfirmationRequired = false
                        },
                        titleString = stringResource(id = R.string.delete_note_dialog),
                        textString = stringResource(id = R.string.are_you_sure_to_delete_your_note)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // color box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(note.color))
            ) {
                Text(text = "")
            }
        }
    }
}