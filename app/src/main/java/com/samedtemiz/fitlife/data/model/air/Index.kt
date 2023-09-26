package com.samedtemiz.fitlife.data.model.air

data class Index(
    val aqi: Int,
    val aqiDisplay: String,
    val category: String,
    val code: String,
    val color: Color,
    val displayName: String,
    val dominantPollutant: String
)