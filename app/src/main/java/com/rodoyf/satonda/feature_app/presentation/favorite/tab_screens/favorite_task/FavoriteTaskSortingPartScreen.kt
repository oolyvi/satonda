package com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.ui.theme.ralewayFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTaskSortingPartScreen(
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

    val itemsList = listOf(
        stringResource(id = R.string.none),
        stringResource(id = R.string.modified_descending),
        stringResource(id = R.string.modified_ascending),
        stringResource(id = R.string.title_a_z),
        stringResource(id = R.string.title_z_a)
    )

    var selectedItem by remember {
        mutableStateOf(itemsList[0])
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp)
            ) {
                items(itemsList) { item ->
                    FilterChip(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .clickableWithoutRipple { },
                        shape = RoundedCornerShape(20.dp),
                        selected = (item == selectedItem),
                        onClick = {
                            selectedItem = item
                            when (item) {
                                "None" -> {
                                    ModifiedDescending()
                                }

                                "Modified Descending" -> {
                                    ModifiedDescending()
                                }

                                "Modified Ascending" -> {
                                    ModifiedAscending()
                                }

                                "Title (A-Z)" -> {
                                    TitleAZ()
                                }

                                "Title (Z-A)" -> {
                                    TitleZA()
                                }
                            }
                        },
                        label = {
                            Text(
                                text = item,
                                color =
                                if (item == selectedItem) {
                                    MaterialTheme.colorScheme.onSurface
                                } else {
                                    MaterialTheme.colorScheme.onSurface.copy(0.6f)
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                fontFamily = ralewayFamily
                            )
                        }
                    )
                }
            }
        }
    }
}