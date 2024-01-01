package com.rodoyf.satonda.feature_app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.presentation.task.TaskEvent
import com.rodoyf.satonda.feature_app.presentation.task.TaskViewModel
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick2

@Composable
fun HomeTaskItem(
    task: Task,
    viewModel: TaskViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    var checked by remember {
        mutableStateOf(task.isDone)
    }

    if (!task.isDone) {
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

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LimitedTextTask(
                        text = task.title,
                        maxCharacters = 60,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        singleLine = true,
                        maxLine = 1,
                        modifier = Modifier.fillMaxWidth(0.85f)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(size = 20.dp)
                            .clip(shape = RoundedCornerShape(3.dp))
                            .pulsateClick2()
                            .clickable {
                                checked = !checked
                                viewModel.onEvent(TaskEvent.OnDoneChange(task, checked))
                            }
                            .background(
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = ButtonDefaults.shape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!task.isDone) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onError,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                if (task.description != null) {
                    LimitedTextTask(
                        text = task.description,
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
    }
}

@Composable
fun LimitedTextTask(
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