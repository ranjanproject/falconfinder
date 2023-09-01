package com.example.falconfinder.repository

import com.example.falconfinder.NetworkDataSource
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.VehicleResponse

class StarWarRepository() {

    private val networkDataSource = NetworkDataSource()

    suspend fun getPlanets(): PlanetResponse{
        return onGetPlanetsResponse(networkDataSource.getPlanets())
    }

    private fun onGetPlanetsResponse(planetResponse: PlanetResponse): PlanetResponse{
        planetResponse.forEach {
            it.isActive = true
            it.isSelected = false
        }
        return planetResponse
    }
    suspend fun getVehicle(): VehicleResponse{
        return onGetVehicleResponse(networkDataSource.getRockets())
    }

    private fun onGetVehicleResponse(vehicleResponse: VehicleResponse): VehicleResponse{
        vehicleResponse.forEach {
            it.isActive = true
            it.isSelected = false
        }

        return vehicleResponse
    }

}