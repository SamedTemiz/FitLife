package com.samedtemiz.fitlife.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.fitlife.data.api.Constants.API_KEY
import com.samedtemiz.fitlife.data.api.Constants.API_KEY2
import com.samedtemiz.fitlife.data.api.RetrofitClient
import com.samedtemiz.fitlife.data.api.SpoonApi
import com.samedtemiz.fitlife.data.model.recipe.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RecipeViewModel : ViewModel() {

    private val recipeApi = RetrofitClient.getRetrofit().create(SpoonApi::class.java)

    val recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            try {
                val response = recipeApi.getRandomRecipes(5, API_KEY2)
                if (response.isSuccessful) {
                    recipes.value = response.body()?.recipes ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    suspend fun fetchRecipeDetail(recipeId: Int) {
        try {
            val response = recipeApi.getRecipeInformation(recipeId, false, API_KEY2)
            if (response.isSuccessful) {
                _selectedRecipe.value = response.body()
            } else {
                _selectedRecipe.value = null
            }
        } catch (e: Exception) {
            _selectedRecipe.value = null
        }
    }
}

