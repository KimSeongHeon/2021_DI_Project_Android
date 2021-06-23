package com.distudy.diproject

import android.app.Application
import com.distudy.diproject.dependencyInjection.app.AppComponent
import com.distudy.diproject.dependencyInjection.app.AppModule
import com.distudy.diproject.dependencyInjection.app.DaggerAppComponent

class MyApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}