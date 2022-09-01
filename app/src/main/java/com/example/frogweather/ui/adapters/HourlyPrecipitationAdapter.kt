package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.PrecipitationDetail
import com.example.frogweather.ui.holders.HourlyPrecipitationItemViewHolder
import com.example.frogweather.databinding.OneDayHourlyPrecipitationItemBinding

class HourlyPrecipitationAdapter : ListAdapter<PrecipitationDetail, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HourlyPrecipitationItemViewHolder(OneDayHourlyPrecipitationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HourlyPrecipitationItemViewHolder).bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PrecipitationDetail>() {
            override fun areItemsTheSame(oldItem: PrecipitationDetail, newItem: PrecipitationDetail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PrecipitationDetail, newItem: PrecipitationDetail): Boolean {
                return oldItem.time == newItem.time
            }
        }
    }
}