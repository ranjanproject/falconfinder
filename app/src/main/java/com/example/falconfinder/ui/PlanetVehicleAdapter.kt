package com.example.falconfinder.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.R
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem

class PlanetVehicleAdapter(val itemClickListener: ItemClickListener):
    ListAdapter<Any, RecyclerView.ViewHolder>(PlanetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return  when(viewType){
            R.layout.item_vehicle -> RocketViewHolder.create(parent)
            else -> PlanetViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is RocketViewHolder -> holder.bind(item as VehicleResponseItem, itemClickListener)
            else -> (holder as PlanetViewHolder).bind(item as PlanetResponseItem, itemClickListener)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is VehicleResponseItem -> R.layout.item_vehicle
            else -> R.layout.item_planet
        }
    }
}