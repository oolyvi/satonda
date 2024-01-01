package com.rodoyf.satonda.feature_app.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun ShowBannerAdd() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {

        val currentWidth = LocalConfiguration.current.screenWidthDp

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Bottom)
                .background(MaterialTheme.colorScheme.surface),
            factory = { context ->
                AdView(context).apply {
                    //AdSize.BANNER
                    setAdSize(
                        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                            context,
                            currentWidth
                        )
                    )
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}