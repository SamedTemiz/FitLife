package com.samedtemiz.gastronomyguide.data.use_case

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null
)