package com.distudy.diproject.dependencyInjection.viewModel

import androidx.lifecycle.ViewModel
import com.distudy.diproject.dependencyInjection.ViewModelKey
import com.distudy.diproject.viewModel.OAuthViewModel
import com.distudy.diproject.viewModel.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun userListViewModel(userListViewModel: UserListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OAuthViewModel::class)
    abstract fun oAuthViewModel(oAuthViewModel: OAuthViewModel): ViewModel
}