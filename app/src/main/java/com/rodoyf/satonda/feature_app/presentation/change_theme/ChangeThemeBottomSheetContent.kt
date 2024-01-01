package com.rodoyf.satonda.feature_app.presentation.change_theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleInteractionSource
import com.rodoyf.satonda.feature_app.presentation.util.showInterstialAd
import com.rodoyf.satonda.ui.theme.chewyFamily

@Composable
fun ChangeThemeBottomSheetContent(
    modifier: Modifier = Modifier,
    changeThemeViewModel: ChangeThemeViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    val isSystemInDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(top = 35.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        TextButton(
            interactionSource = remember { NoRippleInteractionSource() },
            onClick = {
//                if (activity != null) {
//                    showInterstialAd(activity, context)
//                }
                changeThemeViewModel.enableDarkTheme(true)
            }
        ) {
            Text(
                text = stringResource(R.string.dark),
                fontFamily = chewyFamily,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        TextButton(
            interactionSource = remember { NoRippleInteractionSource() },
            onClick = {
                changeThemeViewModel.isDarkThemeEnabled
//                if (activity != null) {
//                    showInterstialAd(activity, context)
//                }
                changeThemeViewModel.enableDarkTheme(false)
            }
        ) {
            Text(
                text = stringResource(R.string.light),
                fontFamily = chewyFamily,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
            )
        }

        TextButton(
            interactionSource = remember { NoRippleInteractionSource() },
            onClick = {
//                if (activity != null) {
//                    showInterstialAd(activity, context)
//                }
                changeThemeViewModel.enableDarkTheme(isSystemInDarkTheme)
            }
        ) {
            Text(
                text = stringResource(R.string.defaultText),
                fontFamily = chewyFamily,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        }
    }
}