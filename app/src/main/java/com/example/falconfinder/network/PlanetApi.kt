package com.example.falconfinder.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://findfalcone.geektrust.com/"


/**
 * Retrofit builder
 */

private val httpClient = OkHttpClient().newBuilder()
    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }).build()
@Module
@InstallIn(SingletonComponent::class)
object PlanetApi{
    @get:Provides
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}
