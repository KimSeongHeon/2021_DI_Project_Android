package com.distudy.a2021_di_project_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.distudy.a2021_di_project_android.common.BaseViewModel
import com.distudy.a2021_di_project_android.data.UserProfileInfo
import com.distudy.a2021_di_project_android.repository.Repository
import com.truthbean.logger.LoggerFactory
import io.reactivex.rxjava3.schedulers.Schedulers

class UserListViewModel : BaseViewModel() {
    private val log = LoggerFactory.getLogger(UserListViewModel.TAG)

    private val repository = Repository.getInstance()

    private var _userList = MutableLiveData<List<UserProfileInfo>>()
    val userList: LiveData<List<UserProfileInfo>>
        get() = _userList

    fun loadUserList(since: Int, per_page: Int) {
        addDisposable(repository.loadAllUserList(since, per_page).observeOn(Schedulers.io()).subscribe({ userList ->
            _userList.postValue(userList.userList)
        }, { t: Throwable? ->
            log.error(t?.message)
        }))
    }

    companion object {
        private const val TAG = "UserListViewModel"
    }

}