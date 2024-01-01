package com.rodoyf.satonda.feature_app.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.MobileAds
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.domain.util.findActivity
import com.rodoyf.satonda.feature_app.presentation.change_theme.ChangeThemeViewModel
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick
import com.rodoyf.satonda.feature_app.presentation.util.showInterstialAd
import com.rodoyf.satonda.ui.theme.chewyFamily
import com.rodoyf.satonda.ui.theme.schoolbellFamily
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteAndIconPart(
    changeThemeViewModel: ChangeThemeViewModel = hiltViewModel(),
) {

    // initialize add
    val context = LocalContext.current
    val activity = context.findActivity()
    MobileAds.initialize(context) {}

    var openBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // why you note
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = chewyFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 45.sp,
                        )
                    ) {
                        append(stringResource(R.string.why_you))
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = schoolbellFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 45.sp,
                        )
                    ) {
                        append(stringResource(R.string.note))
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = chewyFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 45.sp
                        )
                    ) {
                        append(stringResource(R.string.question))
                    }
                },
                style = LocalTextStyle.current.copy(lineHeight = 50.sp),
                color = MaterialTheme.colorScheme.onSurface,
            )

            val isSystemInDarkTheme = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(isSystemInDarkTheme) }


            // toggle lamp icon
            IconToggleButton(
                checked = isDarkMode,
                onCheckedChange = { isChecked ->
                    isDarkMode = isChecked

//                    if (activity != null) {
//                        showInterstialAd(activity, context)
//                    }

                    changeThemeViewModel.enableDarkTheme(isDarkMode)
                },
                modifier = Modifier.pulsateClick(),
            ) {
                Icon(
                    painter = painterResource(R.drawable.newyear),
                    contentDescription = stringResource(R.string.lamp_icon),
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(45.dp)
                )
            }
        }
    }

    /// bottom sheet for change theme
    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openBottomSheet = false },
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
                            .width(50.dp)
                            .height(3.dp)
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.change_theme),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        ) {
            HomeBottomSheetContent()
        }
    }
}