package com.example.weatherforecastapp.ui.splash

import android.util.Log
import com.example.weatherforecastapp.common.BaseViewModel
import com.example.weatherforecastapp.navigation.NavigateTo
import com.example.weatherforecastapp.ui.destinations.HomeScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {
    override val isNavigationDestination = true

    fun navigateToHome() {
        Log.d("Home","Navigate to home")
        navigate(NavigateTo(to = HomeScreenDestination, popCurrent = true))
    }
}