package com.rodoyf.satonda.feature_app.presentation.more.components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick
import com.rodoyf.satonda.ui.theme.chewyFamily

@Composable
fun LeavePart() {

    val activity = (LocalContext.current as? Activity)     // for exit function

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(11.dp)
            .pulsateClick()
            .clickableWithoutRipple {
                activity?.finish()
            }
    ) {

        Text(
            text = stringResource(R.string.exit),
            fontFamily = chewyFamily,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            painter = painterResource(id = R.drawable.exit),
            contentDescription = stringResource(R.string.exit_icon),
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(18.dp)
        )
    }
}