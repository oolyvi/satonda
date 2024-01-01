package com.rodoyf.satonda.feature_app.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rodoyf.satonda.R
import com.rodoyf.satonda.ui.theme.ralewayFamily

@Composable
fun EmptySearchScreen() {

    // for panda json
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.panda))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )

    LaunchedEffect(key1 = progress) {
        if (progress == 0f) {
            isPlaying = true
        }
        if (progress == 1f) {
            isPlaying = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition = composition,
            progress = {
                progress
            },
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = stringResource(R.string.search_for_notes),
            fontFamily = ralewayFamily,
            color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.please_enter_the_title_or_description_of_your_notes_on_the_search_bar),
            color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
    }
}