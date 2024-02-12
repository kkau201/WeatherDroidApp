package com.example.weatherforecastapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@Composable
fun mainActivity() = LocalContext.current as MainActivity

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val navigationFlow: Flow<NavigationEvent?> = MutableSharedFlow()

    fun navigate(navigationEvent: NavigationEvent) {
        viewModelScope.launch {
            (navigationFlow as MutableSharedFlow).emit(navigationEvent)
        }
    }
}