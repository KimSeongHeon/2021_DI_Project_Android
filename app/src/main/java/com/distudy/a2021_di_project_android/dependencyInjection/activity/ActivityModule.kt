package com.distudy.a2021_di_project_android.dependencyInjection.activity

import android.app.Activity
import com.distudy.a2021_di_project_android.dependencyInjection.app.AppComponent
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
     val activity: Activity,
     val appComponent: AppComponent
) {
    @Provides
    fun userService() = appComponent.userService()

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun activity() = activity
}