package com.example.frogweather.data.repository

import com.example.frogweather.data.database.SavedWeather
import com.example.frogweather.data.database.SavedWeathersRoomDatabase
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(
    private val database: SavedWeathersRoomDatabase
) {
    fun getSavedWeathers(): Flow<List<SavedWeather>> = database.savedWeatherDao().getSavedWeathers()

    suspend fun insertWeather(savedWeather: SavedWeather){
        database.savedWeatherDao().insert(savedWeather)
    }

    suspend fun updateWeather(savedWeather: SavedWeather){
        database.savedWeatherDao().update(savedWeather)
    }

    suspend fun deleteWeather(location: String){
        database.savedWeatherDao().delete(location)
    }
}