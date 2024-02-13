package com.example.weatherforecastapp.network

import com.example.weatherforecastapp.model.Location
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "geo/1.0/direct")
    suspend fun getLocationFromCity(
        @Query("q") query: String,
        @Query("limit") limit: String = Constants.DEFAULT_CITY_LIMITS,
        @Query("appid") appid: String = Constants.API_KEY
    ): Location

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("cnt") cnt: String = Constants.DEFAULT_DAYS,
        @Query("units") units: String = Constants.DEFAULT_UNITS,
        @Query("appid") appid: String = Constants.API_KEY
    ): Weather
}