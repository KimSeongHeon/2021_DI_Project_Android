package com.distudy.a2021_di_project_android.dependencyInjection.app

import android.app.Application
import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import com.distudy.a2021_di_project_android.common.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {
    @Provides
    @AppScope
    fun retrofit(): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BaseURL).build()
    }

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun userService(): UserService = retrofit().create(UserService::class.java)
}