package com.example.weatherforecastapp.model

data class Weather(
    val city: City,
    val days: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Double
)