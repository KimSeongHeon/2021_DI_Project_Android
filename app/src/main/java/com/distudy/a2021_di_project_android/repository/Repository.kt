package com.distudy.a2021_di_project_android.repository

import com.distudy.a2021_di_project_android.api.RetrofitClient
import com.distudy.a2021_di_project_android.data.UserProfileResponse
import com.truthbean.logger.LoggerFactory
import io.reactivex.rxjava3.core.Single

class Repository {
    private val log = LoggerFactory.getLogger(TAG)

    companion object {
        private const val TAG = "Repository"

        @Volatile
        private var instance: Repository? = null

        @JvmStatic
        fun getInstance(): Repository = instance ?: synchronized(this) {
            instance ?: Repository().also {
                instance = it
            }
        }
    }

    fun loadAllUserList(since: Int, per_page: Int): Single<UserProfileResponse> {
        return RetrofitClient.userService.getAllUserList(since = since, per_page = per_page)
    }
}