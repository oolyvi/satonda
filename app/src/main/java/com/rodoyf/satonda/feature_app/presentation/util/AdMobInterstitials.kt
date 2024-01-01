package com.rodoyf.satonda.feature_app.presentation.util

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

fun showInterstialAd(activity: Activity, context: Context) {

    InterstitialAd.load(
        context,
        "ca-app-pub-3940256099942544/1033173712",
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                interstitialAd.show(activity)
            }
        }
    )
}