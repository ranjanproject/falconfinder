package com.example.falconfinder.services

import com.example.falconfinder.AppConstants
import com.example.falconfinder.models.FalconFinderRequestBody
import com.example.falconfinder.models.FalconFinderResponse
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.Token
import com.example.falconfinder.models.VehicleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PlanetAndRocketServices {

    @GET("planets")
    suspend fun getPlanetList(): PlanetResponse

    @GET("vehicles")
    suspend fun getRocketList(): VehicleResponse

    @POST("token")
    suspend fun getToken(@Header(AppConstants.ACCEPT) accept:String = AppConstants.APPLICATION_JSON_VALUE): Token

    @POST("find")
    suspend fun findFalcon(@Header(AppConstants.ACCEPT) accept:String = AppConstants.APPLICATION_JSON_VALUE,
                           @Header(AppConstants.CONTENT_TYPE_NAME) contentType:String = AppConstants.APPLICATION_JSON_VALUE,
                           @Body falconFinderRequestBody: FalconFinderRequestBody): FalconFinderResponse

}