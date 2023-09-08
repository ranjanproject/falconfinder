package com.example.falconfinder.models

import com.google.gson.annotations.SerializedName

data class FalconFinderRequestBody(
    @SerializedName("token")
    val token: String,
    @SerializedName("planet_names")
    val planets: List<String>,
    @SerializedName("vehicle_names")
    val vehicles: List<String>
)