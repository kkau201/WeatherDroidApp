package com.example.weatherforecastapp.repository

import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.model.City
import com.example.weatherforecastapp.model.Location
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
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }

    suspend fun getForecast(
        city: City
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            weatherApi.getDailyForecast(
                lat = city.coord.lat.toString(),
                long = city.coord.lon.toString()
            )
        } catch (e: Exception) {
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}