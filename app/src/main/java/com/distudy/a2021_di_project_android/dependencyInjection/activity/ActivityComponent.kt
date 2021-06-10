package com.distudy.a2021_di_project_android.dependencyInjection.activity

import android.app.Activity
import android.app.Application
import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun userService(): UserService
    fun application(): Application
}