package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.util.convertLongToTimeForTask
import com.rodoyf.satonda.feature_app.domain.util.convertLongToTimeInHourForTask
import com.rodoyf.satonda.feature_app.presentation.util.DeleteConfirmationDialog
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick2

@Composable
fun FavoriteTaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    viewModel: FavoriteTaskViewModel = hiltViewModel(),
) {

    if (task.isFavorite) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        val parsedTime = convertLongToTimeForTask(task.timestamp)
        val parsedTimeHour =
            convertLongToTimeInHourForTask(task.timestamp)

        // isDone icon
        var checked by remember {
            mutableStateOf(task.isDone)
        }

        val textDecoration =
            if (task.isDone) TextDecoration.LineThrough else TextDecoration.None

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = parsedTime,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = parsedTimeHour,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textAlign = TextAlign.Center,
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(size = 20.dp)
                        .clip(shape = RoundedCornerShape(3.dp))
                        .clickable {
                            checked = !checked
                            viewModel.onEvent(FavoriteTaskEvent.OnDoneChangeTask(task, checked))
                        }
                        .background(
                            color =
                            if (task.isDone) MaterialTheme.colorScheme.onError
                            else MaterialTheme.colorScheme.surface,
                            shape = ButtonDefaults.shape
                        )
                        .border(
                            width = 0.5.dp,
                            color =
                            if (task.isDone) MaterialTheme.colorScheme.onError
                            else MaterialTheme.colorScheme.onSurface,
                            shape = ButtonDefaults.shape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (task.isDone) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.check_icon),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }

                Divider(
                    modifier = Modifier.width(6.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(task.color))
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = task.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = textDecoration,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        )
                    )
                    if (task.description != null) {
                        Text(
                            text = task.description,
                            textDecoration = textDecoration,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Light,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    // favorite icon
                    IconToggleButton(
                        checked = task.isFavorite,
                        onCheckedChange = { isChecked ->
                            viewModel.onEvent(
                                FavoriteTaskEvent.OnFavoriteChangeTask(
                                    task,
                                    isChecked
                                )
                            )
                        },
                        modifier = Modifier.pulsateClick2()
                    ) {
                        Icon(
                            imageVector = if (task.isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Filled.FavoriteBorder
                            },
                            tint = if (task.isFavorite) {
                                MaterialTheme.colorScheme.inverseOnSurface
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                            contentDescription = stringResource(id = R.string.favorite_icon),
                            modifier = Modifier.size(20.dp)
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
                        titleString = stringResource(R.string.delete_task),
                        textString = stringResource(R.string.are_you_sure_to_delete_your_task)
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}