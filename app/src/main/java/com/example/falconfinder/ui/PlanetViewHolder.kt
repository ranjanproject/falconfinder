package com.example.falconfinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.R
import com.example.falconfinder.databinding.ItemPlanetBinding
import com.example.falconfinder.models.PlanetResponseItem

class PlanetViewHolder(private val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root) {

    companion object{

        private const val DONLON = "Donlon"
        private const val ENCHAI = "Enchai"
        private const val JEBING = "Jebing"
        private const val SAPIR = "Sapir"
        private const val LERBIN = "Lerbin"
        private const val PINGSOR = "Pingasor"

        fun create(viewGroup: ViewGroup): PlanetViewHolder{
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ItemPlanetBinding.inflate(inflater, viewGroup, false)
            return PlanetViewHolder(binding)
        }
    }

    fun bind(planetResponseItem: PlanetResponseItem, itemClickListener: ItemClickListener){

        binding.planetNameTv.text = planetResponseItem.name

        binding.distanceTv.text = planetResponseItem.distance.toString()

        binding.planetCl.isSelected = planetResponseItem.isSelected

        planetResponseItem.name?.let { getImageResource(it) }
            ?.let { binding.planetIv.setImageResource(it) }

        if(planetResponseItem.isActive){
            binding.planetCl.setBackgroundResource(R.drawable.item_selector)
        }else{
            binding.planetCl.setBackgroundResource(R.drawable.item_inactive_state)
        }

        if(planetResponseItem.isActive) {
            binding.planetCl.setOnClickListener {

                binding.planetCl.isSelected = !binding.planetCl.isSelected

                itemClickListener.onPlanetClicked(planetResponseItem, binding.planetCl.isSelected)
            }
        }else{
            binding.planetCl.setOnClickListener(null)
        }

    }

    private fun getImageResource(name: String): Int{
        return when(name){
            DONLON -> R.drawable.donlon_6513af62
            ENCHAI -> R.drawable.enchai_1401d00a
            JEBING -> R.drawable.jebing_d45dffae
            SAPIR -> R.drawable.sapir_2bf5a066
            LERBIN -> R.drawable.lerbin_be0b4004
            PINGSOR -> R.drawable.pingasor_d98d786a
            else -> R.drawable.donlon_6513af62
        }
    }



}