package com.example.weatherforecastapp.ui.home

import com.example.weatherforecastapp.model.Weather

sealed class HomeUiState

data object LoadingState: HomeUiState()

data class LoadedState(
    val weather: Weather
): HomeUiState() {

    val cityName: String
        get() = "${weather.city.name}, ${weather.city.country}"
}