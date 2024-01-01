package com.rodoyf.satonda.feature_app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.change_theme.ChangeThemeViewModel

@Composable
fun HomeBottomSheetContent(
    changeThemeViewModel: ChangeThemeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val isSystemInDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(top = 35.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            onClick = {
                changeThemeViewModel.enableDarkTheme(true)
            }
        ) {
            Text(
                text = stringResource(R.string.dark_mode),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            onClick = {
                changeThemeViewModel.enableDarkTheme(false)
            },
        ) {
            Text(
                text = stringResource(R.string.light_mode),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            onClick = {
                changeThemeViewModel.enableDarkTheme(isSystemInDarkTheme)
            }
        ) {
            Text(
                text = stringResource(R.string.system_default),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}