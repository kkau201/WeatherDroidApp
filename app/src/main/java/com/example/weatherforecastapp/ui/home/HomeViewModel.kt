package com.example.weatherforecastapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.common.BaseViewModel
import com.example.weatherforecastapp.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : BaseViewModel() {
    override val isNavigationDestination = true

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(LoadingState)
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            async(Dispatchers.IO) { loadData() }
            // Set _isRefreshing to false to indicate the refresh is complete
            _isRefreshing.emit(false)
        }
    }

    fun loadData() {
        viewModelScope.launch {
            updateLoading(true)
            getLocation().value.data?.get(0)?.let { location ->
                weatherRepo.getForecast(location).data?.let { weather ->
                    _uiState.update { LoadedState(weather) }
                    updateLoading(false)
                }
            }
        }
    }
}