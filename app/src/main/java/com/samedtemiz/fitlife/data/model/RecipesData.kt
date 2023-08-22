package com.samedtemiz.fitlife.data.model

import com.samedtemiz.fitlife.R

data class RecipesData(
    val title: String,
    val readyTime: Int,
    val servings: Int,
    val healthy: Int,
    val imgUri: Int
)

/**
 * create Recipes List
 * */

val recipesList = listOf(
    RecipesData(
        title = "Dulce De Leche Crème Brûlée",
        readyTime = 45,
        servings = 1,
        healthy = 23,
        imgUri = R.drawable.deneme_food
    ),
    RecipesData(
        title = "Dulce De Leche Crème Brûlée",
        readyTime = 45,
        servings = 1,
        healthy = 23,
        imgUri = R.drawable.deneme_food
    ),
    RecipesData(
        title = "Dulce De Leche Crème Brûlée",
        readyTime = 45,
        servings = 1,
        healthy = 23,
        imgUri = R.drawable.deneme_food
    ),
    RecipesData(
        title = "Dulce De Leche Crème Brûlée",
        readyTime = 45,
        servings = 1,
        healthy = 23,
        imgUri = R.drawable.deneme_food
    ),
    RecipesData(
        title = "Dulce De Leche Crème Brûlée",
        readyTime = 45,
        servings = 1,
        healthy = 23,
        imgUri = R.drawable.deneme_food
    )

)
