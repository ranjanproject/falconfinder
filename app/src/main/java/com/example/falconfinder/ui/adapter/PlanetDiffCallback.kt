package com.example.falconfinder.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem

class PlanetDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        var areItemTheSame = false
        if(oldItem is PlanetResponseItem && newItem is PlanetResponseItem){
            areItemTheSame = true
        }
        if(oldItem is VehicleResponseItem && newItem is VehicleResponseItem){
            areItemTheSame = true
        }
        return areItemTheSame
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        var areContentTheSame = false
        if(oldItem is PlanetResponseItem && newItem is PlanetResponseItem){
            areContentTheSame = oldItem == newItem
        }
        if(oldItem is VehicleResponseItem && newItem is VehicleResponseItem){
            areContentTheSame = oldItem.isSelected == newItem.isSelected
        }
        return false
    }
}