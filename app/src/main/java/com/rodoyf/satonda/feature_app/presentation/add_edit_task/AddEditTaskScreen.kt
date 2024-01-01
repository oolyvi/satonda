package com.rodoyf.satonda.feature_app.presentation.add_edit_task

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.presentation.add_edit_task.components.AddEditTaskTopBar
import com.rodoyf.satonda.feature_app.presentation.util.ShowBannerAdd
import com.rodoyf.satonda.feature_app.presentation.util.rememberImeState
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    taskColor: Int,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    val taskTitleState = viewModel.taskTitle.value
    val taskDescriptionState = viewModel.description.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {

                is AddEditTaskViewModel.UiEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.popBackStack()
                }

                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    // ime state
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Scaffold(
        topBar = {
            AddEditTaskTopBar(
                navController = navController
            )
        },
        bottomBar = {
            /// reklam
            if (activity != null) {
                ShowBannerAdd()
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {

            item {
                // general column
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 65.dp)
                        .fillMaxSize()
                ) {

                    // color picker row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surface),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Task.taskColors.forEach { color ->
                            val colorInt = color.toArgb()
                            // border of color picker
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .border(
                                        width = 3.dp,
                                        color = if (viewModel.taskColor.value == colorInt) {
                                            MaterialTheme.colorScheme.onSurface
                                        } else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt))
                                    }
                            )
                        }
                    }

                    TextField(
                        value = taskTitleState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditTaskEvent.EnteredTaskTitle(it))
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Default
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.title),
                                fontWeight = FontWeight.Light,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontStyle = FontStyle.Normal,
                                fontSize = 20.sp,
                            )
                        },
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )

                    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface)

                    TextField(
                        value = taskDescriptionState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditTaskEvent.EnteredDescription(it))
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Default
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.description),
                                fontWeight = FontWeight.Light,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontStyle = FontStyle.Normal,
                                fontSize = 15.sp,
                            )
                        },
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}