package com.example.frogweather.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.databinding.OneDayDetailsBinding

class DetailsViewHolder(private val binding: OneDayDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(details: OneDayListItem.DetailsDataClass) {
        binding.apply {
            detailsHumidityValue.text = itemView.resources.getString(R.string.lbl_humidity_value).format(details.humidity)
            detailsPressureValue.text = itemView.resources.getString(details.pressureLabel).format(details.pressureValue)
            detailsVisibilityValue.text = itemView.resources.getString(details.visibilityLabel).format(details.visibilityValue)
        }
    }
}
