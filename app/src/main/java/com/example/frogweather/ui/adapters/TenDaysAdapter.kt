package com.example.frogweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.TenDaysListItemState
import com.example.frogweather.data.classes.TenDaysListItem
import com.example.frogweather.databinding.TenDaysListItemBinding
import com.example.frogweather.ui.holders.TenDaysListItemViewHolder

class TenDaysAdapter(private val listener: TenDaysListItemState) : ListAdapter<TenDaysListItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TenDaysListItemViewHolder(TenDaysListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TenDaysListItemViewHolder).bind(getItem(position), listener)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TenDaysListItem>() {
            override fun areItemsTheSame(oldItem: TenDaysListItem, newItem: TenDaysListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TenDaysListItem, newItem: TenDaysListItem): Boolean {
                return oldItem.dayOfWeek == newItem.dayOfWeek
            }
        }
    }
}