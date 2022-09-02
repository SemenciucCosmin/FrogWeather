package com.example.frogweather.ui.holders

import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.TenDaysListItem
import com.example.frogweather.data.classes.TenDaysListItemState
import com.example.frogweather.databinding.TenDaysListItemBinding
import com.example.frogweather.ui.adapters.TenDaysHourlyForecastAdapter

class TenDaysListItemViewHolder(private val binding: TenDaysListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tenDaysListItem: TenDaysListItem, listener: TenDaysListItemState) {
        binding.tenDaysListItemHeader.apply {
            headerDate.text =
                itemView.resources.getString(R.string.lbl_ten_days_header_date).format(tenDaysListItem.dayOfWeek, tenDaysListItem.dayOfMonth)
            headerForecastState.text = tenDaysListItem.forecastState.replaceFirstChar(Char::uppercase)
            headerPrecipitationChance.isVisible = tenDaysListItem.chance != 0
            headerPrecipitationChance.text = itemView.resources.getString(R.string.lbl_ten_days_header_chance).format(tenDaysListItem.chance)
            headerMaxTemperature.text = itemView.resources.getString(R.string.lbl_ten_days_header_temperature).format(tenDaysListItem.maxTemperature)
            headerMinTemperature.text = itemView.resources.getString(R.string.lbl_ten_days_header_temperature).format(tenDaysListItem.minTemperature)
        }

        binding.tenDaysListItemDetails.apply {
            detailsVolumeValue.text = tenDaysListItem.volumeValue.toString()
            detailsVolumeUnit.text = itemView.resources.getString(tenDaysListItem.volumeLabel)
            detailsWindValue.text = tenDaysListItem.windSpeed.speedValue.toString()
            detailsWindUnit.text = itemView.resources.getString(tenDaysListItem.speedType.shortResourceId)
            detailsWindDirectionIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, tenDaysListItem.directionType.resourceId))
            detailsPressureValue.text = itemView.resources.getString(tenDaysListItem.pressureLabel).format(tenDaysListItem.pressureValue)
            detailsHumidityValue.text = itemView.resources.getString(R.string.lbl_ten_days_header_chance).format(tenDaysListItem.humidityValue)
            detailsSunriseValue.text = tenDaysListItem.sunriseTime
            detailsSunsetValue.text = tenDaysListItem.sunsetTime
        }
        val tenDaysHourlyForecastAdapter = TenDaysHourlyForecastAdapter()
        binding.tenDaysListItemHourlyForecast.hourlyForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = tenDaysHourlyForecastAdapter
            setHasFixedSize(true)
        }
        tenDaysHourlyForecastAdapter.submitList(tenDaysListItem.hourlyForecast)

        binding.tenDaysListItemHourlyForecast.hourlyForecastRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding.tenDaysListItemGroup.visibility = if (!tenDaysListItem.isCollapsed) View.VISIBLE else View.GONE
        binding.tenDaysListItemHeader.root.setOnClickListener {
            listener.onItemClicked(tenDaysListItem)
            binding.tenDaysListItemGroup.visibility = if (!tenDaysListItem.isCollapsed) View.VISIBLE else View.GONE
        }
    }
}
