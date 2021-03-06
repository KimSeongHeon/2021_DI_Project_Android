package com.distudy.diproject.common

import com.distudy.diproject.data.AccessToken
import com.distudy.diproject.dependencyInjection.app.AppScope
import javax.inject.Inject

@AppScope
class AccessTokenController @Inject constructor() {
    var currentToken: AccessToken? = null
}