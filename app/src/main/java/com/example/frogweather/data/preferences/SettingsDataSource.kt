package com.example.frogweather.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.frogweather.data.utils.DEFAULT_COORDINATE
import com.example.frogweather.data.utils.DEFAULT_MILLIS
import com.example.frogweather.data.utils.DISTANCE_KM
import com.example.frogweather.data.utils.LENGTH_MILLIMETERS
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_SIMPLE
import com.example.frogweather.data.utils.PRESSURE_HPA
import com.example.frogweather.data.utils.SETTINGS_PREFERENCE_NAME
import com.example.frogweather.data.utils.SPEED_METERS
import com.example.frogweather.data.utils.TEMPERATURE_CELSIUS
import com.example.frogweather.data.utils.WIND_DIRECTION_ARROWS
import com.example.frogweather.data.classes.MyLocation
import com.example.frogweather.data.classes.Settings
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_PREFERENCE_NAME)

class SettingsDataSource(context: Context) {
    private val detectLocation = booleanPreferencesKey("detect_location")
    private val hourFormat = booleanPreferencesKey("hour_format")
    private val temperatureUnit = stringPreferencesKey("temperature_unit")
    private val lengthUnit = stringPreferencesKey("length_unit")
    private val speedUnit = stringPreferencesKey("speed_unit")
    private val distanceUnit = stringPreferencesKey("distance_unit")
    private val pressureUnit = stringPreferencesKey("pressure_unit")
    private val windDirection = stringPreferencesKey("wind_direction")
    private val showNotification = booleanPreferencesKey("show_notification")
    private val notificationType = stringPreferencesKey("notification_type")
    private val latitude = doublePreferencesKey("latitude")
    private val longitude = doublePreferencesKey("longitude")
    private val lastLocationUpdate = longPreferencesKey("last_location_update")

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
            val detectLocation = preferences[detectLocation] ?: false
            val hourFormatUnit = preferences[hourFormat] ?: true
            val temperatureUnit = preferences[temperatureUnit] ?: TEMPERATURE_CELSIUS
            val lengthUnit = preferences[lengthUnit] ?: LENGTH_MILLIMETERS
            val speedUnit = preferences[speedUnit] ?: SPEED_METERS
            val distanceUnit = preferences[distanceUnit] ?: DISTANCE_KM
            val pressureUnit = preferences[pressureUnit] ?: PRESSURE_HPA
            val windDirection = preferences[windDirection] ?: WIND_DIRECTION_ARROWS
            val showNotification = preferences[showNotification] ?: false
            val notificationType = preferences[notificationType] ?: NOTIFICATION_TYPE_SIMPLE

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

    val locationFlow: Flow<MyLocation> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val latitude = preferences[latitude] ?: DEFAULT_COORDINATE
            val longitude = preferences[longitude] ?: DEFAULT_COORDINATE
            val millis = preferences[lastLocationUpdate] ?: DEFAULT_MILLIS
            MyLocation(millis, latitude, longitude)
        }

    suspend fun saveLocationToPreferenceStore(location: MyLocation, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[latitude] = location.latitude
            preferences[longitude] = location.longitude
            preferences[lastLocationUpdate] = location.millis
        }
    }

    suspend fun saveDetectLocationToPreferenceStore(detectLocation: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.detectLocation] = detectLocation
        }
    }

    suspend fun saveHourFormatToPreferenceStore(hourFormat: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.hourFormat] = hourFormat
        }
    }

    suspend fun saveTemperatureUnitToPreferenceStore(temperatureUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.temperatureUnit] = temperatureUnit
        }
    }

    suspend fun saveLengthUnitToPreferenceStore(lengthUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.lengthUnit] = lengthUnit
        }
    }

    suspend fun saveSpeedUnitToPreferenceStore(speedUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.speedUnit] = speedUnit
        }
    }

    suspend fun saveDistanceUnitToPreferenceStore(distanceUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.distanceUnit] = distanceUnit
        }
    }

    suspend fun savePressureUnitToPreferenceStore(pressureUnit: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.pressureUnit] = pressureUnit
        }
    }

    suspend fun saveWindDirectionToPreferenceStore(windDirection: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.windDirection] = windDirection
        }
    }

    suspend fun saveShowNotificationToPreferenceStore(showNotification: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.showNotification] = showNotification
        }
    }

    suspend fun saveNotificationTypeToPreferenceStore(notificationType: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[this.notificationType] = notificationType
        }
    }
}