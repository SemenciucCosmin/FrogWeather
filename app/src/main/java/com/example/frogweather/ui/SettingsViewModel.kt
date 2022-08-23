package com.example.frogweather.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.frogweather.data.LOCATION_UPDATE_INTERVAL
import com.example.frogweather.data.MINUTES_DIVIDER
import com.example.frogweather.data.Settings
import com.example.frogweather.data.SettingsDataSource
import com.example.frogweather.data.SettingsRepository
import com.example.frogweather.data.MyLocation
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsRepository = SettingsRepository(SettingsDataSource(application))

    fun getSettings(): LiveData<Settings> {
        return settingsRepository.getSettings().asLiveData()
    }

    fun getLocation(): LiveData<MyLocation> {
        return settingsRepository.getLocation().asLiveData()
    }

    fun saveLocation(location: MyLocation, context: Context) {
        viewModelScope.launch {
            settingsRepository.getLocation().collect { oldLocation ->
                val minutes = (location.millis - oldLocation.millis) / MINUTES_DIVIDER
                if (minutes > LOCATION_UPDATE_INTERVAL) {
                    settingsRepository.saveLocationToPreferenceStore(location, context)
                }
            }
        }
    }

    fun saveDetectLocation(detectLocation: Boolean, context: Context) {
        viewModelScope.launch { settingsRepository.saveDetectLocationToPreferenceStore(detectLocation, context) }
    }

    fun saveHourFormat(hourFormat: Boolean, context: Context) {
        viewModelScope.launch { settingsRepository.saveHourFormatToPreferenceStore(hourFormat, context) }
    }

    fun saveTemperatureUnit(temperatureUnit: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveTemperatureUnitToPreferenceStore(temperatureUnit, context) }
    }

    fun saveLengthUnit(lengthUnit: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveLengthUnitToPreferenceStore(lengthUnit, context) }
    }

    fun saveSpeedUnit(speedUnit: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveSpeedUnitToPreferenceStore(speedUnit, context) }
    }

    fun saveDistanceUnit(distanceUnit: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveDistanceUnitToPreferenceStore(distanceUnit, context) }
    }

    fun savePressureUnit(pressureUnit: String, context: Context) {
        viewModelScope.launch { settingsRepository.savePressureUnitToPreferenceStore(pressureUnit, context) }
    }

    fun saveWindDirection(windDirection: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveWindDirectionToPreferenceStore(windDirection, context) }
    }

    fun saveShowNotification(showNotification: Boolean, context: Context) {
        viewModelScope.launch { settingsRepository.saveShowNotificationToPreferenceStore(showNotification, context) }
    }

    fun saveNotificationType(notificationType: String, context: Context) {
        viewModelScope.launch { settingsRepository.saveNotificationTypeToPreferenceStore(notificationType, context) }
    }

    class SettingsViewModelFactory(val app: FrogWeatherApplication) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}