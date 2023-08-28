package com.example.falconfinder.services

import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.VehicleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlanetAndRocketServices {

    @GET("planets")
    suspend fun getPlanetList(): PlanetResponse

    @GET("vehicles")
    suspend fun getRocketList(): VehicleResponse

    @POST("token")
    suspend fun getToken(): String

    @POST("find")
    suspend fun findFalcon(@Body token: String,
                           @Body planet_names: List<String>): Boolean

}