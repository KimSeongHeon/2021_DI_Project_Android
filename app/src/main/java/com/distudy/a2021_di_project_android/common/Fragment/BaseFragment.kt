package com.distudy.a2021_di_project_android.common.Fragment

import androidx.fragment.app.Fragment
import com.distudy.a2021_di_project_android.common.Activity.BaseActivity
import com.distudy.a2021_di_project_android.dependencyInjection.fragment.DaggerPresentationComponent
import com.distudy.a2021_di_project_android.dependencyInjection.fragment.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(
                PresentationModule((requireActivity() as BaseActivity).activityComponent))
                    .build()
    }

    protected val injector get() = presentationComponent
}