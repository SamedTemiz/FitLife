package com.samedtemiz.fitlife.data.model.ingredient

data class Ingredient(
    val aisle: String,
    val amount: Double,
    val categoryPath: List<String>,
    val consistency: String,
    val estimatedCost: EstimatedCost,
    val id: Int,
    val image: String,
    val meta: List<Any>,
    val name: String,
    val nutrition: Nutrition,
    val original: String,
    val originalName: String,
    val possibleUnits: List<String>,
    val shoppingListUnits: List<String>,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)