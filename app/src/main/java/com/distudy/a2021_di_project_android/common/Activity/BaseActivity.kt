package com.distudy.a2021_di_project_android.common.Activity

import androidx.appcompat.app.AppCompatActivity
import com.distudy.a2021_di_project_android.MyApplication
import com.distudy.a2021_di_project_android.dependencyInjection.activity.ActivityComponent
import com.distudy.a2021_di_project_android.dependencyInjection.activity.ActivityModule
import com.distudy.a2021_di_project_android.dependencyInjection.activity.DaggerActivityComponent
import com.distudy.a2021_di_project_android.dependencyInjection.fragment.DaggerPresentationComponent
import com.distudy.a2021_di_project_android.dependencyInjection.fragment.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appComponent))
            .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()
    }

    protected val injector get() = presentationComponent

}