package com.example.frogweather.ui.holders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.WindDetail
import com.example.frogweather.databinding.OneDayHourlyWindItemBinding

class HourlyWindItemViewHolder(private val binding: OneDayHourlyWindItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(windDetail: WindDetail) {
        binding.apply {
            hourlyWindTime.text = windDetail.time
            hourlyWindSpeedValue.text = windDetail.speed.speedValue.toString()
            hourlyWindDirectionIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, windDetail.directionType.resourceId))
            hourlyWindSpeedIcon.setBackgroundColor(ContextCompat.getColor(itemView.context, windDetail.speed.colorResourceId))
            val params = hourlyWindSpeedIcon.layoutParams
            params.height = windDetail.speed.iconHeight
            hourlyWindSpeedIcon.requestLayout()
        }
    }
}
