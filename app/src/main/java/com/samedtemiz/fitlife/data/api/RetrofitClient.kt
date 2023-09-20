package com.samedtemiz.fitlife.data.api

import com.samedtemiz.fitlife.data.api.Constants.BASE_URL
import com.samedtemiz.fitlife.data.api.Constants.GOOGLE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}