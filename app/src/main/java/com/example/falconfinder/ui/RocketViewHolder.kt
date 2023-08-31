package com.example.falconfinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.R
import com.example.falconfinder.databinding.ItemVehicleBinding
import com.example.falconfinder.models.VehicleResponseItem

class RocketViewHolder(private val binding: ItemVehicleBinding):
    RecyclerView.ViewHolder(binding.root) {

        companion object{

            private const val SPACE_ROCKET = "Space rocket"
            private const val SPACE_POD ="Space pod"
            private const val SPACE_SHUTTLE = "Space shuttle"
            private const val SPACE_SHIP = "Space ship"

            fun create(viewGroup: ViewGroup): RocketViewHolder{
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                return RocketViewHolder(ItemVehicleBinding.inflate(layoutInflater, viewGroup, false))
            }
        }

    fun bind(vehicleResponseItem: VehicleResponseItem, itemClickListener: ItemClickListener){

        binding.rocketNameTv.text = vehicleResponseItem.name
        vehicleResponseItem.name?.let { getRocketImage(it) }
            ?.let { binding.rocketIv.setImageResource(it) }
        binding.countTv.text = vehicleResponseItem.total_no.toString()
        binding.maxDistTv.text = vehicleResponseItem.max_distance.toString()
        binding.rocketSpeedTv.text = vehicleResponseItem.speed.toString()
        binding.rocketCl.isSelected = vehicleResponseItem.isSelected

      if(vehicleResponseItem.isActive){
         binding.rocketCl.setBackgroundResource(R.drawable.item_selector)
      }else{
          binding.rocketCl.setBackgroundResource(R.drawable.item_inactive_state)
      }
      if(vehicleResponseItem.isActive) {
          binding.rocketCl.setOnClickListener {
              itemClickListener.onVehicleClickListener(vehicleResponseItem, !binding.rocketCl.isSelected)
          }
      }
    }

    private fun getRocketImage(name: String): Int{
        return when(name){
            SPACE_POD -> R.drawable.spacepod_c287dd04
            SPACE_SHUTTLE -> R.drawable.spaceshuttle_5b65990b
            SPACE_SHIP -> R.drawable.spaceship_205da655
            else -> R.drawable.spacerocket_65bc3c0c
        }
    }

}