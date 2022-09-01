package com.example.frogweather.ui.holders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.PrecipitationDetail
import com.example.frogweather.databinding.OneDayHourlyPrecipitationItemBinding

class HourlyPrecipitationItemViewHolder(private val binding: OneDayHourlyPrecipitationItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(precipitationDetail: PrecipitationDetail) {
        binding.hourlyPrecipitationChanceValue.text = itemView.resources.getString(R.string.lbl_precipitation_chance_value).format(precipitationDetail.chance)
        binding.hourlyPrecipitationTime.text = precipitationDetail.time
        binding.hourlyPrecipitationIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, precipitationDetail.iconResourceId))
        if (precipitationDetail.volume == 0.0) {
            binding.hourlyPrecipitationVolumeValue.text = itemView.resources.getString(R.string.lbl_precipitation_volume_no_value)
        } else {
            binding.hourlyPrecipitationVolumeValue.text = String.format("%.1f", precipitationDetail.volume)
        }
    }
}