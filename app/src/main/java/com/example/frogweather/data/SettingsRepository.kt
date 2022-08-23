package com.example.frogweather.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val settingsDataSource: SettingsDataSource) {

    fun getSettings(): Flow<Settings> = settingsDataSource.settingsFlow

    fun getLocation(): Flow<MyLocation> = settingsDataSource.locationFlow

    suspend fun saveLocationToPreferenceStore(location: MyLocation, context: Context){
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