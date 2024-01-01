package com.rodoyf.satonda.feature_app.presentation.privacy_policy

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrivacyPolicyScreen(
    navController: NavController,
) {

    PrivacyPolicyContent(
        navController = navController
    )
}