package com.rodoyf.satonda.feature_app.presentation.more.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.rodoyf.satonda.R
import com.rodoyf.satonda.feature_app.presentation.util.AnimatableEmptyDialog
import com.rodoyf.satonda.feature_app.presentation.util.clickableWithoutRipple
import com.rodoyf.satonda.feature_app.presentation.util.pulsateClick2
import com.rodoyf.satonda.ui.theme.QrCodeBorder
import com.rodoyf.satonda.ui.theme.QrCodeShare
import com.rodoyf.satonda.ui.theme.chewyFamily
import com.rodoyf.satonda.ui.theme.ralewayFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MoreTopBar() {

    var qrCodeDialogOpened by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.more),
            fontFamily = ralewayFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Icon(
            painter = painterResource(id = R.drawable.qr),
            contentDescription = stringResource(R.string.qr_icon_for_demonstration),
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(23.dp)
                .pulsateClick2()
                .clickableWithoutRipple {
                    qrCodeDialogOpened = true
                },
        )

        // empty dialog
        if (qrCodeDialogOpened) {

            // animation
            val animateTrigger = remember { mutableStateOf(false) }
            LaunchedEffect(key1 = Unit) {
                launch {
                    delay(100)
                    animateTrigger.value = true
                }
            }

            Dialog(
                onDismissRequest = {
                    qrCodeDialogOpened = false
                }
            ) {
                (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.9f)

                AnimatableEmptyDialog(visible = animateTrigger.value) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.qr_share),
                            contentDescription = stringResource(R.string.qr_icon_for_sharing),
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .border(
                                    1.dp,
                                    QrCodeBorder,
                                    RoundedCornerShape(25.dp)
                                )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .clickableWithoutRipple {
                                    val sharingText = """
                                        Write, plan & organize with Satonda for a seamless local experience.
                                        You can download it from this link down below:
                            
                                        https://play.google.com/store/apps/details?id=com.rodoyf.satonda
                                        """.trimIndent()
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, sharingText)
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)

                                    context.startActivity(shareIntent)
                                }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.share_qr),
                                contentDescription = stringResource(R.string.share_icon),
                                tint = QrCodeShare,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                text = stringResource(R.string.share_app),
                                fontFamily = chewyFamily,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                color = QrCodeShare,
                            )
                        }
                    }
                }
            }
        }
    }
}