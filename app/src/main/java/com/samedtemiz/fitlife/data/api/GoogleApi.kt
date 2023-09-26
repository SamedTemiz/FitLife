package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.model.air.AirQualityResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleApi {
    @Headers("Content-Type: application/json")
    @POST("v1/currentConditions:lookup")
    fun getCurrentConditions(
        @Body request: LocationRequest,
        @Query("key") apiKey: String
    ): Call<AirQualityResponse>
}

data class LocationRequest(
    val location: LatLngData
)

data class LatLngData(
    val latitude: Double,
    val longitude: Double
)