package com.samedtemiz.fitlife.data.model.ingredient

data class Ingredient(
    val amount: Double,
    val id: Int,
    val image: String,
    val name: String,
    val calories: Double,
    val fat: Double,
    val protein: Double,
    val sugar: Double
)

