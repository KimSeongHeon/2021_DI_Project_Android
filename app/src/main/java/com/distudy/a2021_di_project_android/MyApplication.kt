package com.distudy.a2021_di_project_android

import android.app.Application
import com.distudy.a2021_di_project_android.dependencyInjection.app.AppComponent
import com.distudy.a2021_di_project_android.dependencyInjection.app.AppModule
import com.distudy.a2021_di_project_android.dependencyInjection.app.DaggerAppComponent

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