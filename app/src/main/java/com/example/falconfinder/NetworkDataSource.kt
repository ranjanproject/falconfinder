package com.example.falconfinder

import com.example.falconfinder.models.FalconFinderRequestBody
import com.example.falconfinder.models.FalconFinderResponse
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.VehicleResponse
import com.example.falconfinder.network.PlanetApi
import com.example.falconfinder.services.PlanetAndRocketServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource {
    private val service = PlanetApi.retrofit.create(PlanetAndRocketServices::class.java)
    suspend fun getPlanets(): PlanetResponse{
        return withContext(Dispatchers.IO) {
            return@withContext service.getPlanetList()
        }
    }

    suspend fun getRockets(): VehicleResponse{
        return withContext(Dispatchers.IO) {
            return@withContext service.getRocketList()
        }
    }

    suspend fun getToken(): String{
        return withContext(Dispatchers.IO){
            return@withContext service.getToken().token
        }
    }

    suspend fun findFalcon(falconFinderRequestBody: FalconFinderRequestBody): FalconFinderResponse{
        return  withContext(Dispatchers.IO){
            return@withContext service.findFalcon(falconFinderRequestBody = falconFinderRequestBody)
        }
    }
}