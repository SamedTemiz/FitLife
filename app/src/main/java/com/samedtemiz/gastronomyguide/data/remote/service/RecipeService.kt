package com.samedtemiz.gastronomyguide.data.remote.service

import com.samedtemiz.gastronomyguide.data.remote.api.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeService {

    fun RetrofitInstance(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}