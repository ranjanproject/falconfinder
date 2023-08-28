package com.example.falconfinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.databinding.ItemPlanetBinding
import com.example.falconfinder.models.PlanetResponseItem

class PlanetViewHolder(private val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root) {

    companion object{
        fun create(viewGroup: ViewGroup): PlanetViewHolder{
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ItemPlanetBinding.inflate(inflater, viewGroup, false)
            return PlanetViewHolder(binding)
        }
    }

    fun bind(planetResponseItem: PlanetResponseItem, itemClickListener: ItemClickListener){

        binding.planetNameTv.text = planetResponseItem.name

        binding.distanceTv.text = planetResponseItem.distance.toString()

    }

}