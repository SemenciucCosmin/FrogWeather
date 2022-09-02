package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.WindDetail
import com.example.frogweather.databinding.OneDayHourlyWindItemBinding
import com.example.frogweather.ui.holders.HourlyWindItemViewHolder

class HourlyWindAdapter : ListAdapter<WindDetail, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HourlyWindItemViewHolder(OneDayHourlyWindItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HourlyWindItemViewHolder).bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WindDetail>() {
            override fun areItemsTheSame(oldItem: WindDetail, newItem: WindDetail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WindDetail, newItem: WindDetail): Boolean {
                return oldItem.time == newItem.time
            }
        }
    }
}
