package com.example.falconfinder.models

import com.google.gson.annotations.SerializedName

data class FalconFinderRequestBody(
    @SerializedName("token")
    val token: String,
    @SerializedName("planet_name")
    val planets: List<String>,
    @SerializedName("vehicle_name")
    val vehicles: List<String>
)