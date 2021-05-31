package com.distudy.a2021_di_project_android.repository

import retrofit2.Retrofit

object RetrofitClient {
    private var instance: Retrofit? = null

    fun getInstance(url: String): Retrofit? {
        return Retrofit.Builder().baseUrl(url).build()
    }
}