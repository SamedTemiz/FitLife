package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.model.recipe.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonApi {
    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipeResponse>

    @GET("recipes/{recipeId}/information")
    suspend fun getRecipeInformation(
        @Path("recipeId") recipeId: Int,
        @Query("includeNutrition") includeNutrition: Boolean,
        @Query("apiKey") apiKey: String
    ): Response<Recipe>
}

data class RecipeResponse(val recipes: List<Recipe>)

