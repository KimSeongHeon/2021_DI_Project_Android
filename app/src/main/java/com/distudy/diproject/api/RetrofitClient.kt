package com.distudy.diproject.api

import com.distudy.diproject.api.remoteApi.UserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BaseURL = "https://api.github.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(BaseURL).build()
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}