package com.example.frogweather.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "savedWeather")
data class SavedWeather (
    @PrimaryKey
    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "current")
    val current: Int,

    @ColumnInfo(name = "feelsLike")
    val feelsLike: Int,

    @ColumnInfo(name = "min")
    val min: Int,

    @ColumnInfo(name = "max")
    val max: Int
)