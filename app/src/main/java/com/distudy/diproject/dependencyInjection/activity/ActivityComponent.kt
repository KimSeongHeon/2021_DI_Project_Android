package com.distudy.diproject.dependencyInjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.distudy.diproject.dependencyInjection.fragment.PresentationComponent
import com.distudy.diproject.dependencyInjection.fragment.PresentationModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}