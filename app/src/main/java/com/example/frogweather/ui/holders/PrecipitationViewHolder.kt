package com.example.frogweather.ui.holders

import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.databinding.OneDayPrecipitationBinding
import com.example.frogweather.ui.adapters.HourlyPrecipitationAdapter

class PrecipitationViewHolder(private val binding: OneDayPrecipitationBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(precipitation: OneDayListItem.PrecipitationDataClass) {
        binding.precipitationVolumeLabel.text =
            itemView.resources.getString(R.string.lbl_precipitation_volume).format(itemView.resources.getString(precipitation.volumeUnit))
        val hourlyPrecipitationAdapter = HourlyPrecipitationAdapter()
        binding.hourlyPrecipitationRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyPrecipitationAdapter
            setHasFixedSize(true)
        }
        hourlyPrecipitationAdapter.submitList(precipitation.hourlyPrecipitation)

        binding.hourlyPrecipitationRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
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
