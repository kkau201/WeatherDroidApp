package com.example.weatherforecastapp.utils

import android.content.Context
import com.example.weatherforecastapp.R
import kotlin.math.roundToInt

fun Double.formatTempToCelsius(context: Context) : String {
    return context.getString(R.string.celsius_temp, this.roundToInt().toString())
}