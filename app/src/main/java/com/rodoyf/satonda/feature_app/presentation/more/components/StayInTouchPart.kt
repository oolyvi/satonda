package com.rodoyf.satonda.feature_app.presentation.more.components

import android.annotation.SuppressLint
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
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pressClickEffect

@SuppressLint("IntentReset")
@Composable
fun StayInTouchPart() {

    var interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.stay_in_touch),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            // twitter row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        interactionSource = interactionSource

                        val twitterIntent: Intent =
                            Uri
                                .parse("https://twitter.com/rodoyf_tech")
                                .let { webpage ->
                                    Intent(Intent.ACTION_VIEW, webpage)
                                }
                        try {
                            startActivity(context, twitterIntent, null)
                        } catch (e: ActivityNotFoundException) {

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
                        painter = painterResource(id = R.drawable.twitter),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.twitter_or_x_icon),
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.x),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // github row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        interactionSource = interactionSource

                        val githubIntent: Intent =
                            Uri
                                .parse("https://github.com/oolyvi/Satonda")
                                .let { webpage ->
                                    Intent(Intent.ACTION_VIEW, webpage)
                                }
                        try {
                            startActivity(context, githubIntent, null)
                        } catch (e: ActivityNotFoundException) {

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
                        painter = painterResource(id = R.drawable.github),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.github_icon),
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.github),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // contact us row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        interactionSource =
                            interactionSource
                        val contactUsIntent = Intent(Intent.ACTION_SENDTO).apply {
                            type = "text/plain"
                            setData(Uri.parse("mailto:"))
                            putExtra(
                                Intent.EXTRA_EMAIL,
                                arrayOf("rodoyf.corp@gmail.com")
                            )
                            putExtra(Intent.EXTRA_SUBJECT, "Send Feedback")
                        }
                        try {
                            startActivity(context, contactUsIntent, null)
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
                        painter = painterResource(id = R.drawable.mail),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.mail_icon),
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.contact_us),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(R.string.rodoyf_corp_gmail_com),
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