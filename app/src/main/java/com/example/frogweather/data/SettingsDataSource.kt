package com.example.frogweather.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_PREFERENCE_NAME)

class SettingsDataSource(context: Context) {
    private val DETECT_LOCATION = booleanPreferencesKey("detect_location")
    private val HOUR_FORMAT = booleanPreferencesKey("hour_format")
    private val TEMPERATURE_UNIT = stringPreferencesKey("temperature_unit")
    private val LENGTH_UNIT = stringPreferencesKey("length_unit")
    private val SPEED_UNIT = stringPreferencesKey("speed_unit")
    private val DISTANCE_UNIT = stringPreferencesKey("distance_unit")
    private val PRESSURE_UNIT = stringPreferencesKey("pressure_unit")
    private val WIND_DIRECTION = stringPreferencesKey("wind_direction")
    private val SHOW_NOTIFICATION = booleanPreferencesKey("show_notification")
    private val NOTIFICATION_TYPE = stringPreferencesKey("notification_type")

    val settingsFlow: Flow<Settings> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val detectLocation = preferences[DETECT_LOCATION] ?: false
            val hourFormatUnit = preferences[HOUR_FORMAT] ?: false
            val temperatureUnit = preferences[TEMPERATURE_UNIT] ?: TEMPERATURE_CELSIUS
            val lengthUnit = preferences[LENGTH_UNIT] ?: LENGTH_MILLIMETERS
            val speedUnit = preferences[SPEED_UNIT] ?: SPEED_METERS
            val distanceUnit = preferences[DISTANCE_UNIT] ?: DISTANCE_KM
            val pressureUnit = preferences[PRESSURE_UNIT] ?: PRESSURE_MMHG
            val windDirection = preferences[WIND_DIRECTION] ?: WIND_DIRECTION_ARROWS
            val showNotification = preferences[SHOW_NOTIFICATION] ?: false
            val notificationType = preferences[NOTIFICATION_TYPE] ?: NOTIFICATION_TYPE_SIMPLE

            Settings(
                detectLocation,
                hourFormatUnit,
                temperatureUnit,
                lengthUnit,
                speedUnit,
                distanceUnit,
                pressureUnit,
                windDirection,
                showNotification,
                notificationType
            )
        }

    suspend fun saveDetectLocationToPreferenceStore(detectLocation: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[DETECT_LOCATION] = detectLocation
        }
    }

    suspend fun saveHourFormatToPreferenceStore(hourFormat: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[HOUR_FORMAT] = hourFormat
        }
    }

    suspend fun saveTemperatureUnitToPreferenceStore(temperatureUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[TEMPERATURE_UNIT] = temperatureUnit
        }
    }

    suspend fun saveLengthUnitToPreferenceStore(lengthUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[LENGTH_UNIT] = lengthUnit
        }
    }

    suspend fun saveSpeedUnitToPreferenceStore(speedUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[SPEED_UNIT] = speedUnit
        }
    }

    suspend fun saveDistanceUnitToPreferenceStore(distanceUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[DISTANCE_UNIT] = distanceUnit
        }
    }

    suspend fun savePressureUnitToPreferenceStore(pressureUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[PRESSURE_UNIT] = pressureUnit
        }
    }

    suspend fun saveWindDirectionToPreferenceStore(windDirection: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[WIND_DIRECTION] = windDirection
        }
    }

    suspend fun saveShowNotificationToPreferenceStore(showNotification: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_NOTIFICATION] = showNotification
        }
    }

    suspend fun saveNotificationTypeToPreferenceStore(notificationType: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION_TYPE] = notificationType
        }
    }
}