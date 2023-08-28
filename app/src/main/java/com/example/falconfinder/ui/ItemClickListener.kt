package com.example.falconfinder.ui

import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem

interface ItemClickListener {

    fun onPlanetClicked(planetResponseItem: PlanetResponseItem)

    fun onVehicleClickListener(vehicleResponseItem: VehicleResponseItem)

}