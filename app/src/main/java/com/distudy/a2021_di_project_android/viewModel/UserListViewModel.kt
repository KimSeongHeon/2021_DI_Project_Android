package com.distudy.a2021_di_project_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.distudy.a2021_di_project_android.data.UserProfileInfo
import com.distudy.a2021_di_project_android.repository.Repository

class UserListViewModel : ViewModel() {
    private val repository = Repository.getInstance()

    private var _userList = MutableLiveData<List<UserProfileInfo>>()
    val userList: LiveData<List<UserProfileInfo>>
        get() = _userList

    fun loadUserList(since: Int, per_page: Int) {

        repository.loadAllUserList(since, per_page)
    }

}