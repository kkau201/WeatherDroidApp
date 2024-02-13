package com.example.weatherforecastapp.repository

import android.util.Log
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.model.Location
import com.example.weatherforecastapp.model.LocationItem
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getLocation(
        cityQuery: String
    ) : DataOrException<Location, Boolean, Exception> {
        val response = try {
            weatherApi.getLocationFromCity(cityQuery)
        } catch (e: Exception) {
            Log.d("WeatherRepo", "Unable to get location: $e")
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }

    suspend fun getForecast(
        location: LocationItem
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            weatherApi.getDailyForecast(
                lat = location.lat.toString(),
                lon = location.lon.toString()
            )
        } catch (e: Exception) {
            Log.d("WeatherRepo", "Unable to get forecast: $e")
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}