package com.example.falconfinder.repository

import com.example.falconfinder.NetworkDataSource
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.VehicleResponse

class StarWarRepository() {

    private val networkDataSource = NetworkDataSource()

    suspend fun getPlanets(): PlanetResponse{
        return networkDataSource.getPlanets()
    }

    suspend fun getVehicle(): VehicleResponse{
        return networkDataSource.getRockets()
    }

}