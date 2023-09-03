package com.example.falconfinder.network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://findfalcone.geektrust.com/"


/**
 * Retrofit builder
 */

@Module
object PlanetApi{
    @get:Provides
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
