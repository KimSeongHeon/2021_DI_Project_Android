package com.distudy.diproject.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.common.BaseViewModel
import com.distudy.diproject.data.UserProfileInfo
import com.distudy.diproject.repository.Repository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    @Inject
    lateinit var accessTokenController: AccessTokenController

    private var _userList = MutableLiveData<List<UserProfileInfo>>()
    val userList: LiveData<List<UserProfileInfo>>
        get() = _userList

    fun loadUserList(since: Int, per_page: Int) {
        val token = accessTokenController.currentToken
        addDisposable(
            repository.loadAllUserList(token, since, per_page).observeOn(Schedulers.io()).subscribe({ userList ->
                _userList.postValue(userList)
            }, { t: Throwable? ->
                Log.e(TAG, t?.message.toString())
            })
        )
    }

    companion object {
        private const val TAG = "UserListViewModel"
    }

}