package com.example.falconfinder.ui.listeners

import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem

interface ItemClickListener {

    fun onPlanetClicked(planetResponseItem: PlanetResponseItem, isSelected: Boolean)

    fun onVehicleClickListener(vehicleResponseItem: VehicleResponseItem, isSelected: Boolean)

}