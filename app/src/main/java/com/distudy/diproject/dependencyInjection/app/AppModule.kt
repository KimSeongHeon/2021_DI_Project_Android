package com.distudy.diproject.dependencyInjection.app

import android.app.Application
import com.distudy.diproject.api.remoteApi.OAuthService
import com.distudy.diproject.api.remoteApi.UserService
import com.distudy.diproject.common.URLProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule(private val application: Application) {
    @Named(BASE_RETROFIT)
    @Provides
    @AppScope
    fun baseRetrofit(urlProvider: URLProvider, gson: Gson): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(urlProvider.getBaseUrl()).build()
    }

    @Named(OAUTH_RETROFIT)
    @Provides
    @AppScope
    fun oAuthRetrofit(urlProvider: URLProvider, gson: Gson): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(urlProvider.getOAuthUrl()).build()
    }

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun urlProvider() = URLProvider()

    @Provides
    fun gson() = GsonBuilder().setLenient().create()

    @Provides
    @AppScope
    fun userService(@Named(BASE_RETROFIT) retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Provides
    @AppScope
    fun oAuthService(@Named(OAUTH_RETROFIT) retrofit: Retrofit): OAuthService = retrofit.create(OAuthService::class.java)

    companion object {
        private const val BASE_RETROFIT = "BaseRetrofit"
        private const val OAUTH_RETROFIT = "OAuthRetrofit"
    }
}