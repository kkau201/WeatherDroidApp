package com.example.weatherforecastapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.components.WeatherStateImage
import com.example.weatherforecastapp.mainActivity
import com.example.weatherforecastapp.model.Forecast
import com.example.weatherforecastapp.ui.theme.AppTheme
import com.example.weatherforecastapp.utils.Constants
import com.example.weatherforecastapp.utils.formatTempToCelsius
import com.example.weatherforecastapp.utils.formatUnixTimeToDay

@Composable
fun WeekWeatherSection(weekForecast: List<Forecast>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background)
    ) {
        Text(
            text = stringResource(R.string.this_week_title),
            fontWeight = FontWeight.SemiBold,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier.padding(AppTheme.spacing.mdSpacing)
        )
        weekForecast.forEach {
            WeekWeatherListItem(it)
        }
        Spacer(modifier = Modifier.height(AppTheme.spacing.xlgSpacing))
    }
}

@Composable
fun WeekWeatherListItem(forecast: Forecast) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.mdSpacing, vertical = AppTheme.spacing.xsmSpacing)
            .background(color = Color.White, shape = RoundedCornerShape(AppTheme.spacing.lgSpacing))
            .padding(AppTheme.spacing.smSpacing)
    ) {
        Text(text = forecast.dt.formatUnixTimeToDay())
        WeatherStateImage(imgUrl = "${Constants.WEATHER_ICON_BASE_URL}${forecast.weather[0].icon}.png")
        WeatherStateChip(state = forecast.weather[0].description)
        WeekMaxMinTemp(minTemp = forecast.temp.min, maxTemp = forecast.temp.max)
    }
}

@Composable
fun WeatherStateChip(state: String) {
    Text(text = state,
        modifier = Modifier
            .background(color = AppTheme.colors.secondary, shape = CircleShape)
            .padding(AppTheme.spacing.xxsmSpacing)
    )
}

@Composable
fun WeekMaxMinTemp(minTemp: Double, maxTemp: Double) {
    Row {
        Text(
            text = minTemp.formatTempToCelsius(mainActivity()),
            color = AppTheme.colors.tertiary,
            modifier = Modifier.padding(end = AppTheme.spacing.xxsmSpacing)
        )
        Text(
            text = maxTemp.formatTempToCelsius(mainActivity()),
            color = AppTheme.colors.surface
        )
    }
}