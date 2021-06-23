package com.distudy.diproject.dependencyInjection.fragment

import com.distudy.diproject.MainActivity
import com.distudy.diproject.ui.UserListFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: UserListFragment)
    fun inject(mainActivity: MainActivity)
}