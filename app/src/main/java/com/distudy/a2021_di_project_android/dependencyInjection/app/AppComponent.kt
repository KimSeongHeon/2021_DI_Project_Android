package com.distudy.a2021_di_project_android.dependencyInjection.app

import com.distudy.a2021_di_project_android.dependencyInjection.activity.ActivityComponent
import com.distudy.a2021_di_project_android.dependencyInjection.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}