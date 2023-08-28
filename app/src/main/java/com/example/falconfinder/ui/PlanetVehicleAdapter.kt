package com.example.falconfinder.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.models.PlanetResponseItem

class PlanetVehicleAdapter(val itemClickListener: ItemClickListener):
    ListAdapter<Any, RecyclerView.ViewHolder>(PlanetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder = PlanetViewHolder.create(parent)

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlanetViewHolder).bind(getItem(position) as PlanetResponseItem, itemClickListener)
    }

}