package com.example.weatherforecastapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.components.WeatherStateImage
import com.example.weatherforecastapp.mainActivity
import com.example.weatherforecastapp.model.Forecast
import com.example.weatherforecastapp.ui.theme.AppTheme
import com.example.weatherforecastapp.ui.theme.Typography
import com.example.weatherforecastapp.utils.Constants.WEATHER_ICON_BASE_URL
import com.example.weatherforecastapp.utils.formatTempToCelsius

@Composable
fun TodayWeatherSection(
    forecast: Forecast
) {
    val imgUrl = "$WEATHER_ICON_BASE_URL${forecast.weather[0].icon}.png"

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tue 13 Feb, 2024",
            style = Typography.labelMedium,
            modifier = Modifier.padding(vertical = AppTheme.spacing.smSpacing)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = AppTheme.colors.secondary,
                    shape = CircleShape
                )
        ) {
            WeatherStateImage(imgUrl = imgUrl)
            Text(
                text = forecast.temp.day.formatTempToCelsius(mainActivity()),
                style = Typography.headlineLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = forecast.weather[0].main,
                style = Typography.bodySmall,
                modifier = Modifier.padding(bottom = AppTheme.spacing.smSpacing)
            )
        }
    }
}