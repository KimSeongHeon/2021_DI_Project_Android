package com.distudy.a2021_di_project_android.dependencyInjection.app

import android.app.Application
import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun userService(): UserService
    fun application(): Application
}