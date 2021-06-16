package com.distudy.a2021_di_project_android.dependencyInjection.activity

import com.distudy.a2021_di_project_android.dependencyInjection.fragment.PresentationComponent
import com.distudy.a2021_di_project_android.dependencyInjection.fragment.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule) : PresentationComponent
}