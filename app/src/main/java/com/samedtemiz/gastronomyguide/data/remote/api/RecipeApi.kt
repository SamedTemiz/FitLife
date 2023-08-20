package com.samedtemiz.gastronomyguide.data.remote.api

import com.samedtemiz.gastronomyguide.data.remote.model.Recipe
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes/random?apiKey=47fb11970371438ebeef3917f823abf7")
    suspend fun getRandomRecipe(): RecipeResponse
}
data class RecipeResponse(val recipes: List<Recipe>)