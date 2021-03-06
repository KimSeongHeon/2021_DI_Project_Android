package com.distudy.diproject.common.Fragment

import androidx.fragment.app.Fragment
import com.distudy.diproject.common.Activity.BaseActivity
import com.distudy.diproject.dependencyInjection.fragment.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent
}