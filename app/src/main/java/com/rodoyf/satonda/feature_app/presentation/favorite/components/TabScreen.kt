package com.rodoyf.satonda.feature_app.presentation.favorite.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rodoyf.satonda.feature_app.presentation.favorite.FavoriteViewModel
import com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_note.TabFavoriteNote
import com.rodoyf.satonda.feature_app.presentation.favorite.tab_screens.favorite_task.TabFavoriteTask
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleInteractionSource
import com.rodoyf.satonda.ui.theme.ralewayFamily

@SuppressLint("UnrememberedMutableState")
@Composable
fun TabScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {

    val tabIndex = viewModel.tabIndex.observeAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex.value!!,
        ) {
            viewModel.tabs.forEachIndexed { index, title ->
                val isSelected = tabIndex.value!! == index

                Tab(
                    modifier = Modifier
                        .height(50.dp),
                    interactionSource = remember { NoRippleInteractionSource() },
                    text = {
                        Text(
                            text = title,
                            color =
                            if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.onSurface.copy(
                                0.6f
                            ),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = ralewayFamily
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        viewModel.updateTabIndex(index)
                    }
                )
            }
        }

        when (tabIndex.value) {
            0 -> TabFavoriteNote(navController = navController)
            1 -> TabFavoriteTask(navController = navController)
        }
    }
}