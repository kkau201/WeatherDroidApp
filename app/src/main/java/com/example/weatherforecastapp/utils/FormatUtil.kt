package com.example.weatherforecastapp.utils

import android.content.Context
import com.example.weatherforecastapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Double.formatTempToCelsius(context: Context) : String {
    return context.getString(R.string.celsius_temp, this)
}

fun Int.formatUnixTimeToDate() : String {
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val date = Date(this.toLong() * 1000)
    return sdf.format(date)
}