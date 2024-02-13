package com.example.weatherforecastapp.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.components.WeatherStateImage
import com.example.weatherforecastapp.mainActivity
import com.example.weatherforecastapp.model.Forecast
import com.example.weatherforecastapp.ui.theme.AppTheme
import com.example.weatherforecastapp.ui.theme.Typography
import com.example.weatherforecastapp.utils.Constants.WEATHER_ICON_BASE_URL
import com.example.weatherforecastapp.utils.formatTempToCelsius
import com.example.weatherforecastapp.utils.formatUnixTimeToDate
import com.example.weatherforecastapp.utils.formatUnixTimeToTime

@Composable
fun TodayWeatherSection(
    forecast: Forecast
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TodayWeatherTemp(forecast = forecast)
        TodayWeatherHWPDetails(humidity = forecast.humidity, pressure = forecast.pressure, gust = forecast.gust)
        Divider()
        TodayWeatherSSDetails(sunrise = forecast.sunrise, sunset = forecast.sunset)
    }
}

@Composable
fun TodayWeatherTemp(forecast: Forecast) {
    val imgUrl = "$WEATHER_ICON_BASE_URL${forecast.weather[0].icon}.png"

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = AppTheme.spacing.mdSpacing)
    ) {
        Text(
            text = forecast.dt.formatUnixTimeToDate(),
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

@Composable
fun TodayWeatherHWPDetails(humidity: Int, pressure: Int, gust: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(AppTheme.spacing.smSpacing)
            .fillMaxWidth()
    ) {
        TodayWeatherDetailsItem(
            text = stringResource(R.string.humidity_percentage, humidity),
            icon = R.drawable.humidity,
            desc = stringResource(R.string.cont_desc_humidity_icon)
        )
        TodayWeatherDetailsItem(
            text = stringResource(R.string.psi, pressure),
            icon = R.drawable.pressure,
            desc = stringResource(R.string.cont_desc_pressure_icon)
        )
        TodayWeatherDetailsItem(
            text = stringResource(R.string.kph, gust),
            icon = R.drawable.wind,
            desc = stringResource(R.string.cont_desc_wind_icon)
        )
    }
}

@Composable
fun TodayWeatherSSDetails(sunrise: Int, sunset: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(AppTheme.spacing.smSpacing)
            .fillMaxWidth()
    ) {
        TodayWeatherDetailsItem(
            text = sunrise.formatUnixTimeToTime(),
            icon = R.drawable.sunrise,
            desc = stringResource(R.string.cont_desc_sunrise_icon)
        )
        TodayWeatherDetailsItem(
            text = sunset.formatUnixTimeToTime(),
            icon = R.drawable.sunset,
            desc = stringResource(R.string.cont_desc_sunset_icon)
        )
    }
}

@Composable
fun TodayWeatherDetailsItem(
    text: String,
    @DrawableRes icon: Int,
    desc: String
) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = desc,
            modifier = Modifier
                .padding(end = AppTheme.spacing.xsmSpacing)
                .size(20.dp)
        )
        Text(text = text, style = AppTheme.typography.labelLarge)
    }
}