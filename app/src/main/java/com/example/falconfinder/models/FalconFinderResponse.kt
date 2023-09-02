package com.example.falconfinder.models

data class FalconFinderResponse(
    val planetName: String,
    var status: String="",
    var error: String=""
)
