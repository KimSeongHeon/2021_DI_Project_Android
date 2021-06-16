package com.distudy.a2021_di_project_android.dependencyInjection.activity

import android.app.Activity
import android.app.Application
import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import com.distudy.a2021_di_project_android.dependencyInjection.app.AppComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun userService(): UserService
    fun application(): Application
}