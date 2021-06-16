package com.distudy.a2021_di_project_android.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.distudy.a2021_di_project_android.common.BaseViewModel
import com.distudy.a2021_di_project_android.data.UserProfileInfo
import com.distudy.a2021_di_project_android.repository.Repository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    private var _userList = MutableLiveData<List<UserProfileInfo>>()
    val userList: LiveData<List<UserProfileInfo>>
        get() = _userList

    fun loadUserList(since: Int, per_page: Int) {
        addDisposable(repository.loadAllUserList(since, per_page).observeOn(Schedulers.io()).subscribe({ userList ->
            _userList.postValue(userList)
        }, { t: Throwable? ->
            Log.e(TAG, t?.message.toString())
        }))
    }

    companion object {
        private const val TAG = "UserListViewModel"
    }

}