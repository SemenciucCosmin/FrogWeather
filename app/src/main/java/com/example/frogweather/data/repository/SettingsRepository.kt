package com.example.frogweather.data.repository

import android.content.Context
import com.example.frogweather.data.classes.MyLocation
import com.example.frogweather.data.classes.Settings
import com.example.frogweather.data.preferences.SettingsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class SettingsRepository(private val settingsDataSource: SettingsDataSource) {

    fun getSettings(): Flow<Settings> = settingsDataSource.settingsFlow

    fun getLocation(): Flow<MyLocation> = settingsDataSource.locationFlow

    fun getSettingsAdnLocation(): Flow<Pair<Settings, MyLocation>> = settingsDataSource.settingsFlow.combine(settingsDataSource.locationFlow) {
            settings: Settings, location: MyLocation -> Pair(settings, location) }

    suspend fun saveLocationToPreferenceStore(location: MyLocation, context: Context) {
        settingsDataSource.saveLocationToPreferenceStore(location, context)
    }

    suspend fun saveDetectLocationToPreferenceStore(detectLocation: Boolean, context: Context) {
        settingsDataSource.saveDetectLocationToPreferenceStore(detectLocation, context)
    }

    suspend fun saveHourFormatToPreferenceStore(hourFormat: Boolean, context: Context) {
        settingsDataSource.saveHourFormatToPreferenceStore(hourFormat, context)
    }

    suspend fun saveTemperatureUnitToPreferenceStore(temperatureUnit: String, context: Context) {
        settingsDataSource.saveTemperatureUnitToPreferenceStore(temperatureUnit, context)
    }

    suspend fun saveLengthUnitToPreferenceStore(lengthUnit: String, context: Context) {
        settingsDataSource.saveLengthUnitToPreferenceStore(lengthUnit, context)
    }

    suspend fun saveSpeedUnitToPreferenceStore(speedUnit: String, context: Context) {
        settingsDataSource.saveSpeedUnitToPreferenceStore(speedUnit, context)
    }

    suspend fun saveDistanceUnitToPreferenceStore(distanceUnit: String, context: Context) {
        settingsDataSource.saveDistanceUnitToPreferenceStore(distanceUnit, context)
    }

    suspend fun savePressureUnitToPreferenceStore(pressureUnit: String, context: Context) {
        settingsDataSource.savePressureUnitToPreferenceStore(pressureUnit, context)
    }

    suspend fun saveWindDirectionToPreferenceStore(windDirection: String, context: Context) {
        settingsDataSource.saveWindDirectionToPreferenceStore(windDirection, context)
    }

    suspend fun saveShowNotificationToPreferenceStore(showNotification: Boolean, context: Context) {
        settingsDataSource.saveShowNotificationToPreferenceStore(showNotification, context)
    }

    suspend fun saveNotificationTypeToPreferenceStore(notificationType: String, context: Context) {
        settingsDataSource.saveNotificationTypeToPreferenceStore(notificationType, context)
    }

}