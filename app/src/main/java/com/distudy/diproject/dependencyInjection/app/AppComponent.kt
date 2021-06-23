package com.distudy.diproject.dependencyInjection.app

import com.distudy.diproject.dependencyInjection.activity.ActivityComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponentBuilder(): ActivityComponent.Builder
}