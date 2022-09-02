package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.data.enums.OneDayItemViewType
import com.example.frogweather.databinding.OneDayChanceBinding
import com.example.frogweather.databinding.OneDayDetailsBinding
import com.example.frogweather.databinding.OneDayHourlyForecastBinding
import com.example.frogweather.databinding.OneDayMainForecastBinding
import com.example.frogweather.databinding.OneDayPrecipitationBinding
import com.example.frogweather.databinding.OneDayWindBinding
import com.example.frogweather.ui.holders.ChanceViewHolder
import com.example.frogweather.ui.holders.CurrentForecastViewHolder
import com.example.frogweather.ui.holders.DetailsViewHolder
import com.example.frogweather.ui.holders.HourlyForecastViewHolder
import com.example.frogweather.ui.holders.HourlyWindViewHolder
import com.example.frogweather.ui.holders.PrecipitationViewHolder

class OneDayItemViewAdapter : ListAdapter<OneDayListItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (OneDayItemViewType.getByItemViewType(viewType)) {
            OneDayItemViewType.CURRENT_FORECAST -> {
                CurrentForecastViewHolder(OneDayMainForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            OneDayItemViewType.HOURLY_FORECAST -> {
                HourlyForecastViewHolder(OneDayHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            OneDayItemViewType.CHANCE -> {
                ChanceViewHolder(OneDayChanceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            OneDayItemViewType.DETAILS -> {
                DetailsViewHolder(OneDayDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            OneDayItemViewType.PRECIPITATION -> {
                PrecipitationViewHolder(OneDayPrecipitationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                HourlyWindViewHolder(OneDayWindBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).itemViewType) {
            OneDayItemViewType.CURRENT_FORECAST -> (holder as CurrentForecastViewHolder).bind(getItem(position) as OneDayListItem.CurrentForecastDataClass)
            OneDayItemViewType.HOURLY_FORECAST -> (holder as HourlyForecastViewHolder).bind(getItem(position) as OneDayListItem.HourlyForecastDataClass)
            OneDayItemViewType.CHANCE -> (holder as ChanceViewHolder).bind(getItem(position) as OneDayListItem.ChanceDataClass)
            OneDayItemViewType.DETAILS -> (holder as DetailsViewHolder).bind(getItem(position) as OneDayListItem.DetailsDataClass)
            OneDayItemViewType.PRECIPITATION -> (holder as PrecipitationViewHolder).bind(getItem(position) as OneDayListItem.PrecipitationDataClass)
            OneDayItemViewType.WIND -> (holder as HourlyWindViewHolder).bind(getItem(position) as OneDayListItem.WindDataClass)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemViewType.viewType
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<OneDayListItem>() {
            override fun areItemsTheSame(oldItem: OneDayListItem, newItem: OneDayListItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OneDayListItem, newItem: OneDayListItem): Boolean {
                return oldItem.content == newItem.content
            }
        }
    }
}
