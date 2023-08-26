package com.samedtemiz.fitlife.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.fitlife.data.api.RecipeApi
import com.samedtemiz.fitlife.data.api.RetrofitClient
import com.samedtemiz.fitlife.data.model.recipe.Recipe
import kotlinx.coroutines.launch


class RecipeViewModel : ViewModel() {

    private val recipeApi = RetrofitClient.getRetrofit().create(RecipeApi::class.java)

    val recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            try {
                val response = recipeApi.getRandomRecipes(5, "47fb11970371438ebeef3917f823abf7")
                if (response.isSuccessful) {
                    recipes.value = response.body()?.recipes ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

