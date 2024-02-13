package com.example.weatherforecastapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherforecastapp.common.ViewModelBinding
import com.example.weatherforecastapp.model.Weather
import com.example.weatherforecastapp.widgets.WeatherAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    ViewModelBinding(viewModel = viewModel, navigator = navigator)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.refresh() })
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { WeatherAppBar(title = (uiState.value as? LoadedState)?.cityName) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.value is LoadedState) {
                HomeContent(weather = (uiState.value as LoadedState).weather, modifier = Modifier.padding(padding))
            }
            PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })
}

@Composable
fun HomeContent(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        TodayWeatherSection(dayForecast = weather.list[0])
        WeekWeatherSection(weekForecast = weather.list)
    }
}