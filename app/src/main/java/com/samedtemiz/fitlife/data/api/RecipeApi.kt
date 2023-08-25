package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.api.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipeResponse>
}

data class RecipeResponse(val recipes: List<Recipe>)