package com.example.frogweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frogweather.data.CallResult
import com.example.frogweather.data.NetworkRepository
import com.example.frogweather.data.Forecast
import kotlinx.coroutines.launch

class NetworkViewModel : ViewModel() {
    private val networkRepository = NetworkRepository()
    private val _uiState = MutableLiveData<UIState>().apply {
        value = UIState(isLoading = false, forecast = null)
    }
    val uiState = _uiState

    fun requestDailyForecast(latitude: Double, longitude: Double, millis: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(isLoading = true)
            when (val result = networkRepository.getDailyForecast(latitude, longitude, millis)) {
                is CallResult.Success -> _uiState.value = _uiState.value?.copy(isLoading = false, forecast = result.data, errorMessage = null)
                is CallResult.Error -> _uiState.value = _uiState.value?.copy(isLoading = false, errorMessage = "Connection not available.")
            }
        }
    }

    fun requestHourlyForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(isLoading = true)
            when (val result = networkRepository.getHourlyForecast(latitude, longitude)) {
                is CallResult.Success -> _uiState.value = _uiState.value?.copy(isLoading = false, forecast = result.data, errorMessage = null)
                is CallResult.Error -> _uiState.value = _uiState.value?.copy(isLoading = false, errorMessage = "Connection not available.")
            }
        }
    }
}

data class UIState(
    val isLoading: Boolean,
    val forecast: Forecast?,
    val errorMessage: String? = null
)