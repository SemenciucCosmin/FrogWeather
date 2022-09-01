package com.example.frogweather.data

import com.example.frogweather.data.classes.TenDaysListItem

interface TenDaysListItemState {
    fun onItemClicked(tenDaysListItem: TenDaysListItem)
}