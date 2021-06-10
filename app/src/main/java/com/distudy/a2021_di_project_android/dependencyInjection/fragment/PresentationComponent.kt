package com.distudy.a2021_di_project_android.dependencyInjection.fragment

import com.distudy.a2021_di_project_android.ui.UserListFragment
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
}