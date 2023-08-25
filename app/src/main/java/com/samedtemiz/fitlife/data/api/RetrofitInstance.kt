package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.api.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val recipeApi: RecipeApi by lazy {
        retrofit.create(RecipeApi::class.java)
    }
}
