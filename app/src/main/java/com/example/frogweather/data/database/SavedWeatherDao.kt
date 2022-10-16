package com.example.frogweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedWeatherDao {

    @Query("SELECT * FROM savedWeather")
    fun getSavedWeathers(): Flow<List<SavedWeather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(savedWeather: SavedWeather)

    @Update
    suspend fun update(savedWeather: SavedWeather)

    @Query("DELETE FROM savedWeather WHERE location=:location")
    suspend fun delete(location: String)
}