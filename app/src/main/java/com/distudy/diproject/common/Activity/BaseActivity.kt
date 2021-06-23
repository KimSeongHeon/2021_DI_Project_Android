package com.distudy.diproject.common.Activity

import androidx.appcompat.app.AppCompatActivity
import com.distudy.diproject.MyApplication
import com.distudy.diproject.dependencyInjection.activity.ActivityComponent
import com.distudy.diproject.dependencyInjection.activity.ActivityModule
import com.distudy.diproject.dependencyInjection.fragment.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponentBuilder().activity(this).activityModule(ActivityModule).build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent

}