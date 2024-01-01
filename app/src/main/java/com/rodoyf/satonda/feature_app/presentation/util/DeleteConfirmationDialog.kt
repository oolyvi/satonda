package com.rodoyf.satonda.feature_app.presentation.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rodoyf.satonda.R

@Composable
fun DeleteConfirmationDialog(
    onDeleteClick: () -> Unit,
    onDeleteCancel: () -> Unit,
    titleString: String,
    textString: String,
) {

    AlertDialog(
        onDismissRequest = {
            /* Do nothing */
        },
        title = {
            Text(titleString)
        },
        text = {
            Text(textString)
        },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(
                    text = stringResource(R.string.no),
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteClick) {
                Text(
                    text = stringResource(R.string.yes)
                )
            }
        },
    )
}