package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.frogweather.data.classes.SavedWeatherEntry
import com.example.frogweather.databinding.SavedWeatherItemBinding
import com.example.frogweather.ui.holders.SavedWeatherViewHolder

class SavedWeathersAdapter : ListAdapter<SavedWeatherEntry, SavedWeatherViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWeatherViewHolder {
        return SavedWeatherViewHolder(SavedWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SavedWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<SavedWeatherEntry>() {
            override fun areItemsTheSame(oldItem: SavedWeatherEntry, newItem: SavedWeatherEntry): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SavedWeatherEntry, newItem: SavedWeatherEntry): Boolean {
                return oldItem.location == newItem.location
            }
        }
    }
}
