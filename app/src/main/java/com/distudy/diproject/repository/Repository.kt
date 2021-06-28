package com.distudy.diproject.repository

import android.util.Log
import com.distudy.diproject.api.remoteApi.OAuthService
import com.distudy.diproject.api.remoteApi.UserService
import com.distudy.diproject.data.AccessToken
import com.distudy.diproject.data.UserProfileInfo
import com.distudy.diproject.utils.SecureInfoUtil
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class Repository @Inject constructor(private val userService: UserService, private val oAuthService: OAuthService) {
    fun loadAllUserList(token : AccessToken? = null,since: Int, per_page: Int): Single<List<UserProfileInfo>> {
        val accessToken = token?.accessToken
        return userService.getAllUserList(token = accessToken, since = since, per_page = per_page)
    }

    fun getAccessToken(code: String): Single<AccessToken> {
        return oAuthService.getAccessToken(SecureInfoUtil.OAuth_ID, SecureInfoUtil.OAuth_SECRET, code)
    }
}