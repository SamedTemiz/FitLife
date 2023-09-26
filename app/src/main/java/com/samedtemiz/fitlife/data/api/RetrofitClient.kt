package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.api.Constants.GOOGLE_URL
import com.samedtemiz.fitlife.data.api.Constants.SPOON_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SPOON_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getGoogleRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(GOOGLE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}