package com.distudy.a2021_di_project_android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.distudy.a2021_di_project_android.viewModel.UserListViewModel

class UserListFragment: Fragment() {
    private lateinit var binding: fragmentUserListBinding
    private val viewModel: UserListViewModel by viewModels()

    companion object {
        private const val TAG = "UserListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun initViewModel() {

    }
}