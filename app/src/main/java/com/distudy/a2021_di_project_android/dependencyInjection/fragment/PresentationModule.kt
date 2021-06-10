package com.distudy.a2021_di_project_android.dependencyInjection.fragment

import com.distudy.a2021_di_project_android.dependencyInjection.activity.ActivityComponent
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityComponent: ActivityComponent) {
    @Provides
    fun activity() = activityComponent.activity()
    @Provides
    fun application() = activityComponent.application()
    @Provides
    fun userService() = activityComponent.userService()
}