package com.example.frogweather.ui.holders

import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.databinding.OneDayWindBinding
import com.example.frogweather.ui.adapters.HourlyWindAdapter

class HourlyWindViewHolder(private val binding: OneDayWindBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(wind: OneDayListItem.WindDataClass) {
        binding.apply {
            windValue.text = wind.hourlyWind.firstOrNull()?.speed?.speedValue?.toString() ?: ""
            windSpeedUnit.text = itemView.resources.getString(wind.speedUnit)
            windGustValue.text = wind.hourlyWind.firstOrNull()?.speed?.gust?.toString() ?: ""
            windDirection.text = itemView.resources.getString(wind.hourlyWind.firstOrNull()?.directionType?.directionType ?: R.string.lbl_direction_n)
            windDirectionIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    wind.hourlyWind.firstOrNull()?.directionType?.resourceId ?: R.drawable.ic_arrow_n
                )
            )
            val hourlyWindAdapter = HourlyWindAdapter()
            hourlyWindRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = hourlyWindAdapter
                setHasFixedSize(true)
            }
            hourlyWindAdapter.submitList(wind.hourlyWind)

            binding.hourlyWindRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
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
}
