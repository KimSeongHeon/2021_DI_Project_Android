package com.distudy.diproject.api.remoteApi

import com.distudy.diproject.data.AccessToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthService {
    @FormUrlEncoded
    @POST("/access_token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Single<AccessToken>
}