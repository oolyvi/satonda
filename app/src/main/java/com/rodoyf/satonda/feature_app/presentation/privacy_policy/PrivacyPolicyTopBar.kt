package com.rodoyf.satonda.feature_app.presentation.privacy_policy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple

@Composable
fun PrivacyPolicyTopBar(
    navController: NavController,
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 0.dp,
        modifier = Modifier.height(50.dp)
    ) {
        // general
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp)
                .clickableWithoutRipple {
                    navController.navigateUp()
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // back arrow
            Icon(
                painter = painterResource(id = R.drawable.angle_left),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = stringResource(R.string.back_arrow),
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(R.string.privacy_policy),
                color = MaterialTheme.colorScheme.secondaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
            )
        }
    }
}