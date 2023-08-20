package com.samedtemiz.gastronomyguide.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.gastronomyguide.data.remote.api.RecipeApi
import com.samedtemiz.gastronomyguide.data.remote.model.Recipe
import com.samedtemiz.gastronomyguide.data.remote.service.RecipeService
import kotlinx.coroutines.launch


class DetailViewModel : ViewModel() {

    private val _recipeLiveData = MutableLiveData<Recipe>()
    val recipeLiveData: LiveData<Recipe> = _recipeLiveData

    private val recipeApi: RecipeApi

    private var isRecipeFetched = false
    init {
        val retrofit = RecipeService.RetrofitInstance()

        recipeApi = retrofit.create(RecipeApi::class.java)
    }

    fun fetchRandomRecipes() {
        if(!isRecipeFetched){

            viewModelScope.launch {
                val recipeResponse = recipeApi.getRandomRecipe()
                val recipe = recipeResponse.recipes.firstOrNull()
                recipe?.let{
                    _recipeLiveData.postValue(it)
                }

                isRecipeFetched = true
            }

        }

    }
}

