package com.example.weatherforecastapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherforecastapp.R
import kotlinx.coroutines.delay

@Composable
fun LoadingLottie(isLoading: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var play by remember { mutableStateOf(false) }
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_lottie))

        if (play) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }

        LaunchedEffect(key1 = isLoading, block = {
            play = if (isLoading) {
                true
            } else {
                delay(2000L)
                false
            }
        })
    }
}