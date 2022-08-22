package com.example.frogweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frogweather.data.NetworkRepository
import com.example.frogweather.data.Forecast
import kotlinx.coroutines.launch

class NetworkViewModel : ViewModel() {
    private val networkRepository = NetworkRepository()

    fun getForecast(latitude: Double, longitude: Double, millis: Long): LiveData<Forecast> {
        val forecast = MutableLiveData<Forecast>()
        viewModelScope.launch { forecast.value = networkRepository.getForecast(latitude, longitude, millis) }
        return forecast
    }
}