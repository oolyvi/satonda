package com.rodoyf.satonda.feature_app.presentation.task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.task.TaskEvent
import com.rodoyf.satonda.feature_app.presentation.task.TaskViewModel
import com.rodoyf.satonda.feature_app.presentation.task.components.sorting.SortingPartTaskScreen
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSearchBar(
    viewModel: TaskViewModel = hiltViewModel(),
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    SortIsDones: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

    val state = viewModel.state.value

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 15.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = state.searchQuery,
                onValueChange = { newQuery ->
                    viewModel.onEvent(TaskEvent.Search(newQuery))
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(TaskEvent.ClearSearch)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cross_circle),
                                contentDescription = stringResource(id = R.string.clear_icon),
                                tint = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                        modifier = Modifier
                            .size(15.dp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        fontWeight = FontWeight.Light
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.tertiary),
            )

            // sort
            SortingPartTaskScreen(
                ModifiedDescending = {
                    ModifiedDescending()
                },
                ModifiedAscending = {
                    ModifiedAscending()
                },
                SortIsDones = {
                    SortIsDones()
                },
                TitleAZ = {
                    TitleAZ()
                },
                TitleZA = {
                    TitleZA()
                }
            )
        }
    }
}