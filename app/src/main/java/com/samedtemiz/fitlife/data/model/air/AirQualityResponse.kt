package com.samedtemiz.fitlife.data.model.air

data class AirQualityResponse(
    val dateTime: String,
    val indexes: List<Index>,
    val regionCode: String
)