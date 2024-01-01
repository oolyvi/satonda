package com.rodoyf.satonda.ui.theme

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodoyf.satonda.feature_app.presentation.change_theme.ChangeThemeViewModel

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = PurpleGreyDark,
    tertiary = BottomBarDark,
    error = ErrorDark,
    secondaryContainer = IosIconDark,
    surface = PureIosDark,
    onSurface = PureIosLight,
    background = BackgroundDark,
    onBackground = CategoryTaskDark,
    onSecondary = LampDark,
    onSecondaryContainer = ThemeDefaultDark,
    inverseOnSurface = FavoriteIconDark,
    onError = CheckboxDark,
    onTertiary = NewBottomDark,
    onTertiaryContainer = CategoryNoteDark,
    onPrimaryContainer = UnselectedBottomDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = PurpleGreyLight,
    tertiary = BottomBarLight,
    error = ErrorLight,
    secondaryContainer = IosIconLight,
    surface = PureIosLight,
    onSurface = PureIosDark,
    background = BackgroundLight,
    onBackground = CategoryTaskLight,
    onSecondary = LampLight,
    onSecondaryContainer = ThemeDefaultLight,
    inverseOnSurface = FavoriteIconLight,
    onError = CheckboxColorLight,
    onTertiary = NewBottomLight,
    onTertiaryContainer = CategoryNoteLight,
    onPrimaryContainer = UnselectedBottomLight
)


@Composable
fun SatondaTheme(
    dynamicColor: Boolean = false,
    themeViewModel: ChangeThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val darkTheme by themeViewModel.isDarkThemeEnabled.collectAsStateWithLifecycle()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Black.toArgb()
            window.navigationBarColor = colorScheme.onTertiary.toArgb()
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}