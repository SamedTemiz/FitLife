package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.api.RecipeApi
import com.samedtemiz.fitlife.data.api.model.Recipe

class RecipeRepository(private val api: RecipeApi) {

    suspend fun getRandomRecipes(number: Int, apiKey: String): List<Recipe>? {
        return try {
            val response = api.getRandomRecipes(number, apiKey)
            if (response.isSuccessful) {
                response.body()?.recipes
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
