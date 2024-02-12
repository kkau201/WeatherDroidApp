package com.example.weatherforecastapp.ui.home

import com.example.weatherforecastapp.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    override val isNavigationDestination = true
}