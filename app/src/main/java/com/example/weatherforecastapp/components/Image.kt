package com.example.weatherforecastapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherStateImage(imgUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imgUrl),
        contentDescription = "Today's weather icon",
        modifier = Modifier.size(60.dp)
    )
}