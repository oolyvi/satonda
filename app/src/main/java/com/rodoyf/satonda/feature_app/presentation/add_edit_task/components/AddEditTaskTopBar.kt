package com.rodoyf.satonda.feature_app.presentation.add_edit_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.add_edit_task.AddEditTaskEvent
import com.rodoyf.satonda.feature_app.presentation.add_edit_task.AddEditTaskViewModel
import com.rodoyf.satonda.feature_app.presentation.util.AnimatableEmptyDialog
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.ui.theme.ralewayFamily
import com.rodoyf.satonda.ui.theme.schoolbellFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddEditTaskTopBar(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {

    val taskTitleState = viewModel.taskTitle.value
    val taskDescriptionState = viewModel.description.value

    var emptyStateDialogOpened by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(emptyStateDialogOpened) {
        if (taskTitleState.text.isEmpty()) {
            delay(2500)
            emptyStateDialogOpened = false
        }
    }

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // close icon
            Icon(
                painter = painterResource(id = R.drawable.cross),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = stringResource(R.string.close),
                modifier = Modifier
                    .size(16.dp)
                    .clickableWithoutRipple {
                        navController.navigateUp()
                    }
            )

            // task title
            Text(
                text = stringResource(R.string.task),
                fontFamily = ralewayFamily,
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )

            // done icon
            Icon(
                painter = painterResource(id = R.drawable.done),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = stringResource(R.string.done_icon),
                modifier = Modifier
                    .size(20.dp)
                    .clickableWithoutRipple {


                        if (taskTitleState.text.isEmpty() &&
                            (taskDescriptionState.text.isEmpty() || taskDescriptionState.text.isNotEmpty())
                        ) {
                            emptyStateDialogOpened = true
                        } else {
                            viewModel.onEvent(AddEditTaskEvent.SaveTask)
                        }
                    }
            )

            // empty dialog
            if (emptyStateDialogOpened) {

                // animation
                val animateTrigger = remember { mutableStateOf(false) }
                LaunchedEffect(key1 = Unit) {
                    launch {
                        delay(100)
                        animateTrigger.value = true
                    }
                }

                Dialog(
                    onDismissRequest = {
                        emptyStateDialogOpened = false
                    }
                ) {
                    (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)

                    AnimatableEmptyDialog(visible = animateTrigger.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .border(
                                    3.dp,
                                    MaterialTheme.colorScheme.onSurface.copy(0.6f),
                                    RoundedCornerShape(55.dp)
                                )
                                .clip(RoundedCornerShape(55.dp))
                                .background(MaterialTheme.colorScheme.error.copy(0.6f))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(50.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.title_can_t_be_empty),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = schoolbellFamily,
                                    fontSize = 26.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}