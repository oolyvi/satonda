package com.rodoyf.satonda.feature_app.presentation.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String,
    actionLabel: String,
    actionIcon: ImageVector,
    durationInSeconds: Int = 5,
    actionOnClick: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier.padding(bottom = 16.dp, start = 6.dp, end = 6.dp),
                containerColor = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(15.dp),
                action = {
                    Row(
                        modifier = Modifier
                            .clickableWithoutRipple {
                                actionOnClick()
                                snackbarHostState.currentSnackbarData?.dismiss()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.secondaryContainer,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = actionLabel,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            ) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    )

    LaunchedEffect(durationInSeconds) {
        coroutineScope.launch {
            delay(durationInSeconds * 1000L)
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}