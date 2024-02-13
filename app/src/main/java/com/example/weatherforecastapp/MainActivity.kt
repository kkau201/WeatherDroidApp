package com.example.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherforecastapp.components.LoadingLottie
import com.example.weatherforecastapp.navigation.WeatherForecastNavHost
import com.example.weatherforecastapp.ui.theme.WeatherForecastAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    WeatherForecastContent()
                }
            }
        }
    }
}

@Composable
fun WeatherForecastContent(
    mainViewModel: MainViewModel = hiltViewModel(mainActivity())
) {
    WeatherForecastNavHost()

    val loadingState by mainViewModel.loadingFlow.collectAsState()
    LoadingLottie(isLoading = loadingState)
}