package com.example.weatherforecastapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.model.Location
import com.example.weatherforecastapp.navigation.NavigationEvent
import com.example.weatherforecastapp.repository.WeatherRepo
import com.example.weatherforecastapp.utils.Constants.DEFAULT_CITY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@Composable
fun mainActivity() = LocalContext.current as MainActivity

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel(), DefaultLifecycleObserver {
    val navigationFlow: Flow<NavigationEvent?> = MutableSharedFlow()
    val loadingFlow: StateFlow<Boolean> = MutableStateFlow(false)

    private val _location: MutableStateFlow<DataOrException<Location, Boolean, Exception>> = MutableStateFlow(DataOrException(null, true))
    val location
        get() = _location

    init {
        Log.d("MainViewModel", "Calling init")
        loadLocation()
    }

    fun navigate(navigationEvent: NavigationEvent) {
        viewModelScope.launch {
            (navigationFlow as MutableSharedFlow).emit(navigationEvent)
        }
    }

    fun updateLoadingState(isLoading: Boolean) {
        viewModelScope.launch {
            (loadingFlow as MutableStateFlow).emit(isLoading)
        }
    }

    private fun loadLocation() {
        viewModelScope.launch {
            getLocation(DEFAULT_CITY)
        }
    }

    private fun getLocation(city: String) {
        Log.d("MainViewModel", "Get location : $city")
        viewModelScope.launch {
            if(city.isEmpty()) return@launch
            _location.value.loading = true
            _location.value = weatherRepo.getLocation(city)
            _location.value.loading = location.value.data.toString().isEmpty()
            Log.d("MainViewModel", "Updated location: ${location.value.data}")
        }
    }
}