package com.samedtemiz.gastronomyguide.data.remote.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)