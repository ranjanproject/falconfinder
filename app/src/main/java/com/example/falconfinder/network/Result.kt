package com.example.falconfinder.network

sealed class Result{
    data class Success(val response: Any): Result()
    data class Error(val error: Error?): Result()
    data class Failure(val exception: Exception): Result()
}


