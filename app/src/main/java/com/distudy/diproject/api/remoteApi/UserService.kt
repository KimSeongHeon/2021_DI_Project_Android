package com.distudy.diproject.api.remoteApi

import com.distudy.diproject.data.UserProfileInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/users")
    fun getAllUserList(
        @Header("accept") accept: String = "application/vnd.github.v3+json",
        @Header("Authorization") token: String? = "",
        @Query("since") since: Int,
        @Query("per_page") per_page: Int
    ): Single<List<UserProfileInfo>>

    @GET("/users/{username}")
    fun getDetailUserInfo(
        @Header("accept") accept: String,
        @Path("username") username: String
    ): Call<Int>
}