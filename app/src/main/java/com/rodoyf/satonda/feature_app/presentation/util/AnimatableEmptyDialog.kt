package com.rodoyf.satonda.feature_app.presentation.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable

@Composable
internal fun AnimatableEmptyDialog(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = tween(100)
        ),
        exit = scaleOut(
            animationSpec = tween(100)
        ),
        content = content
    )
}