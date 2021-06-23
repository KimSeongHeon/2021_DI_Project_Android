package com.distudy.diproject.common

class URLProvider {
    fun getBaseUrl(): String {
        return Constants.BaseURL
    }

    fun getOAuthUrl(): String {
        return Constants.OAuthURL
    }
}