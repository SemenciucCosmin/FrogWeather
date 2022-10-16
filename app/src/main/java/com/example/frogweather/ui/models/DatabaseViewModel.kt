package com.example.frogweather.ui.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.frogweather.data.classes.SavedWeatherEntry
import com.example.frogweather.data.database.SavedWeather
import com.example.frogweather.data.database.SavedWeathersRoomDatabase
import com.example.frogweather.data.repository.DatabaseRepository
import com.example.frogweather.ui.application.FrogWeatherApplication
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DatabaseRepository(SavedWeathersRoomDatabase.getDatabase(application))

    fun getSavedWeathers(): LiveData<List<SavedWeatherEntry>> {
        return Transformations.map(repository.getSavedWeathers().asLiveData()){ list ->
            list.map {
                SavedWeatherEntry(it.location, it.date, it.description, it.current, it.feelsLike, it.min, it.max)
            }
        }
    }

    fun insertWeather(savedWeather: SavedWeather) =
        viewModelScope.launch { repository.insertWeather(savedWeather) }

    fun updateWeather(savedWeather: SavedWeather) =
        viewModelScope.launch { repository.updateWeather(savedWeather) }

    fun deleteWeather(location: String) =
        viewModelScope.launch { repository.deleteWeather(location) }


    class DatabaseViewModelFactory(val app: FrogWeatherApplication) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DatabaseViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}