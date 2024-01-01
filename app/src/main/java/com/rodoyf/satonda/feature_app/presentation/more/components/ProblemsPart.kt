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
import androidx.core.content.ContextCompat
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pressClickEffect

@SuppressLint("IntentReset")
@Composable
fun ProblemsPart() {

    var interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.problems),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            // report a bug
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .pressClickEffect()
                    .clickableWithoutRipple {
                        interactionSource = interactionSource
                        val contactUsIntent = Intent(Intent.ACTION_SENDTO).apply {
                            type = "text/plain"
                            setData(Uri.parse("mailto:"))
                            putExtra(
                                Intent.EXTRA_EMAIL,
                                arrayOf("rodoyf.corp@gmail.com")
                            )
                            putExtra(Intent.EXTRA_SUBJECT, "Report a bug")
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Please, attach some files to improve debugging speed"
                            )

                        }
                        try {
                            ContextCompat.startActivity(context, contactUsIntent, null)
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
                        painter = painterResource(id = R.drawable.bug),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.bug_icon),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.report_a_bug),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}