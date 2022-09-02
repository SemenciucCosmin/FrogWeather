package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.ForecastDetail
import com.example.frogweather.databinding.OneDayHourlyForecastItemBinding
import com.example.frogweather.ui.holders.HourlyForecastItemViewHolder

class HourlyForecastAdapter : ListAdapter<ForecastDetail, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HourlyForecastItemViewHolder(OneDayHourlyForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HourlyForecastItemViewHolder).bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ForecastDetail>() {
            override fun areItemsTheSame(oldItem: ForecastDetail, newItem: ForecastDetail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ForecastDetail, newItem: ForecastDetail): Boolean {
                return oldItem.time == newItem.time
            }
        }
    }
}
