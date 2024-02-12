package com.example.weatherforecastapp.common

import androidx.lifecycle.ViewModel
import com.example.weatherforecastapp.MainViewModel
import com.example.weatherforecastapp.navigation.NavigateBack
import com.example.weatherforecastapp.navigation.NavigationEvent

abstract class BaseViewModel() : ViewModel() {
    private lateinit var mainViewModel: MainViewModel

    /**
     * If this model is owned by a navigation destination
     * (a composable with `@Destination` annotation)
     */
    open val isNavigationDestination: Boolean = false

    fun setMainViewModel(main: MainViewModel) {
        mainViewModel = main
    }

    fun navigate(navigationEvent: NavigationEvent) {
        mainViewModel.navigate(navigationEvent)
    }

    fun navigateBack() {
        mainViewModel.navigate(NavigateBack)
    }
}