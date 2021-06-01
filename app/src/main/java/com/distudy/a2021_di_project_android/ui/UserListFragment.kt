package com.distudy.a2021_di_project_android.ui

import androidx.fragment.app.Fragment
import com.distudy.a2021_di_project_android.viewModel.UserListViewModel
import androidx.fragment.app.viewModels

class UserListFragment: Fragment() {
    private val viewModel: UserListViewModel by viewModels()

    companion object {
        private const val TAG = "UserListFragment"
    }
}