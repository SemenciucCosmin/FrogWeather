package com.example.frogweather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedWeather::class], version = 1, exportSchema = false)
abstract class SavedWeathersRoomDatabase : RoomDatabase() {

    abstract fun savedWeatherDao(): SavedWeatherDao

    companion object {
        @Volatile
        private var INSTANCE: SavedWeathersRoomDatabase? = null

        fun getDatabase(context: Context): SavedWeathersRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedWeathersRoomDatabase::class.java,
                    "record_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
