package com.example.frogweather.ui.holders

import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.databinding.OneDayHourlyForecastBinding
import com.example.frogweather.ui.adapters.HourlyForecastAdapter

class HourlyForecastViewHolder(private val binding: OneDayHourlyForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hourlyForecast: OneDayListItem.HourlyForecastDataClass) {
        val hourlyForecastAdapter = HourlyForecastAdapter()
        binding.hourlyForecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyForecastAdapter
            setHasFixedSize(true)
        }
        hourlyForecastAdapter.submitList(hourlyForecast.hourlyForecast)

        binding.hourlyForecastRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }
}
