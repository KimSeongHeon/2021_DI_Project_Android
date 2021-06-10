package com.distudy.a2021_di_project_android.repository

import com.distudy.a2021_di_project_android.api.remoteApi.UserService
import com.distudy.a2021_di_project_android.data.UserProfileInfo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(val userService: UserService){
    fun loadAllUserList(since: Int, per_page: Int): Single<List<UserProfileInfo>> {
        return userService.getAllUserList(since = since, per_page = per_page)
    }
}