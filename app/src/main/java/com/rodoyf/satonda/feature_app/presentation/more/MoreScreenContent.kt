package com.rodoyf.satonda.feature_app.presentation.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rodoyf.satonda.feature_app.presentation.change_theme.ChangeThemeBottomSheetContent
import com.rodoyf.satonda.feature_app.presentation.more.components.AppCompPart
import com.rodoyf.satonda.feature_app.presentation.more.components.ChangeThemePart
import com.rodoyf.satonda.feature_app.presentation.more.components.LeavePart
import com.rodoyf.satonda.feature_app.presentation.more.components.MoreTopBar
import com.rodoyf.satonda.feature_app.presentation.more.components.ProblemsPart
import com.rodoyf.satonda.feature_app.presentation.more.components.StayInTouchPart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenContent(
    navController: NavController,
) {

    var openThemeBottomSheet by remember { mutableStateOf(false) }
    val themeBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    // more top bar
    MoreTopBar()

    // change theme
    ChangeThemePart(
        onGoToChangeThemeClick = {
            openThemeBottomSheet = true
        }
    )
    Spacer(modifier = Modifier.height(15.dp))
    Divider(thickness = 0.5.dp)
    Spacer(modifier = Modifier.height(15.dp))

    // stay in touch
    StayInTouchPart()
    Spacer(modifier = Modifier.height(15.dp))
    Divider(thickness = 0.5.dp)
    Spacer(modifier = Modifier.height(15.dp))

    // app components
    AppCompPart(
        navController = navController
    )
    Spacer(modifier = Modifier.height(15.dp))
    Divider(thickness = 0.5.dp)
    Spacer(modifier = Modifier.height(15.dp))

    // problems
    ProblemsPart()
    Spacer(modifier = Modifier.height(30.dp))

    // leave part
    LeavePart()
    Spacer(modifier = Modifier.height(35.dp))

    /// bottom sheet for theme changing
    if (openThemeBottomSheet) {
        ModalBottomSheet(
            sheetState = themeBottomSheetState,
            onDismissRequest = { openThemeBottomSheet = false },
            dragHandle = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    BottomSheetDefaults.DragHandle(
                        modifier = Modifier
                            .width(30.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        ) {
            ChangeThemeBottomSheetContent()
        }
    }
}