package com.rodoyf.satonda.feature_app.presentation.more.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.navigation.Screen
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pressClickEffect

@Composable
fun AppCompPart(
    navController: NavController,
) {

    var interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.app),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {

            // rate and review
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        interactionSource = interactionSource

                        val rateAndReviewIntent: Intent =
                            Uri
                                .parse("https://play.google.com/store/apps/details?id=com.rodoyf.satonda")
                                .let { webpage ->
                                    Intent(Intent.ACTION_VIEW, webpage)
                                }
                        try {
                            startActivity(context, rateAndReviewIntent, null)
                        } catch (_: ActivityNotFoundException) {

                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // logo and name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.star_icon),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.rate_review),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // privacy policy
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        navController.navigate(Screen.PrivacyPolicyScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // logo and name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.privacy_policy),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.privacy_policy_icon),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.privacy_policy),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // terms and conditions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        navController.navigate(Screen.TermsScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // logo and name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.terms),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.terms_icon),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.terms_conditions),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // version part
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // logo and name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.version),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.version_icon),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.version),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(R.string.VERSION_NUMBER),
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}