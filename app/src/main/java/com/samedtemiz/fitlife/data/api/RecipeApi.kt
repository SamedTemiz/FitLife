package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.model.ingredient.Ingredient
import com.samedtemiz.fitlife.data.model.recipe.Recipe
import org.intellij.lang.annotations.Identifier
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipeResponse>


    @GET("food/ingredients/{ingredientId}/information")
    suspend fun getIngredientInformation(
        @Path("ingredientId") ingredientId: Int,
        @Query("amount") amount: Int,
        @Query("apiKey") apiKey: String
    ): Response<Ingredient>
}

data class RecipeResponse(val recipes: List<Recipe>)

