package com.rodoyf.satonda.feature_app.presentation.task.components.sorting

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.NoRippleTheme
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick

@Composable
fun SortBottomSheetContentTask(
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    SortIsDones: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(
                    bottom = 55.dp,
                    top = 25.dp,
                    start = 45.dp,
                    end = 45.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // modified descending
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pulsateClick()
                    .clickableWithoutRipple {
                        ModifiedDescending()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_down),
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    contentDescription = stringResource(id = R.string.down_icon_for_sorting),
                    modifier = Modifier
                        .size(18.dp)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = stringResource(id = R.string.modified_descending),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // modified ascending
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pulsateClick()
                    .clickableWithoutRipple {
                        ModifiedAscending()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_up),
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    contentDescription = stringResource(id = R.string.up_icon_for_sorting),
                    modifier = Modifier
                        .size(18.dp)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = stringResource(id = R.string.modified_ascending),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // completed tasks first
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pulsateClick()
                    .clickableWithoutRipple {
                        SortIsDones()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_check),
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    contentDescription = stringResource(R.string.sorting_just_completed_ones),
                    modifier = Modifier
                        .size(18.dp)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = stringResource(R.string.completed_tasks_first),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // title a-z
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pulsateClick()
                    .clickableWithoutRipple {
                        TitleAZ()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_az),
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    contentDescription = stringResource(id = R.string.a_to_z_icon_for_sorting),
                    modifier = Modifier
                        .size(18.dp)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = stringResource(id = R.string.title_a_z),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // title (Z-A)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pulsateClick()
                    .clickableWithoutRipple {
                        TitleZA()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_za),
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    contentDescription = stringResource(id = R.string.z_to_a_icon_for_sorting),
                    modifier = Modifier
                        .size(18.dp)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = stringResource(id = R.string.title_z_a),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}