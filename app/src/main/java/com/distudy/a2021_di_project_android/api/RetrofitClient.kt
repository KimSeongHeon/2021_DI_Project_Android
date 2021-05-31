package com.distudy.a2021_di_project_android.api

import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import retrofit2.Retrofit

object RetrofitClient {
    private const val BaseURL = "https://api.github.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BaseURL).build()
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}