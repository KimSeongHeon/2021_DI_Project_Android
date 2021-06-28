package com.distudy.diproject.api.remoteApi

import com.distudy.diproject.data.AccessToken
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface OAuthService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Single<AccessToken>
}