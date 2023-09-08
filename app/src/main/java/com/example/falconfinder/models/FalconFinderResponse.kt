package com.example.falconfinder.models

import com.google.gson.annotations.SerializedName

data class FalconFinderResponse(
    @SerializedName("planet_name")
    val planetName: String,
    var status: String="",
    var error: String=""
)
