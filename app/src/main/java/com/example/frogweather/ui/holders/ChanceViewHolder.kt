package com.example.frogweather.ui.holders

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.databinding.OneDayChanceBinding

class ChanceViewHolder(private val binding: OneDayChanceBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chance: OneDayListItem.ChanceDataClass) {
        binding.precipitationLayout.isVisible = chance.chanceValue != 0
        binding.oneDayChanceValue.text = itemView.resources.getString(R.string.lbl_short_precipitation_chance).format(chance.chanceValue)
    }
}