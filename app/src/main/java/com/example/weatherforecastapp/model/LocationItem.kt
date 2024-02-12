package com.example.weatherforecastapp.model

data class LocationItem(
    val country: String,
    val lat: Double,
    val localNames: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)