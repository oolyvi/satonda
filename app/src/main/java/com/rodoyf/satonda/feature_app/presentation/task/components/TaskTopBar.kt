package com.rodoyf.satonda.feature_app.presentation.task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple

@Composable
fun TaskTopBar(
    navController: NavController,
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 0.dp,
        modifier = Modifier.height(50.dp)
    ) {
        // general row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, start = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // back arrow
            Icon(
                painter = painterResource(id = R.drawable.angle_left),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = stringResource(id = R.string.back_arrow),
                modifier = Modifier
                    .size(20.dp)
                    .clickableWithoutRipple {
                        navController.navigateUp()
                    }
            )

            // add button
            Icon(
                painter = painterResource(id = R.drawable.add),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = stringResource(id = R.string.add_icon),
                modifier = Modifier
                    .size(20.dp)
                    .clickableWithoutRipple {
                        navController.navigate(Screen.AddEditTaskScreen.route)
                    }
            )
        }
    }
}