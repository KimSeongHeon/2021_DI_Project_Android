package com.distudy.diproject.viewModel

import android.util.Log
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.common.BaseViewModel
import com.distudy.diproject.repository.Repository
import javax.inject.Inject

class OAuthViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    @Inject
    lateinit var accessTokenHelper: AccessTokenController

    fun getAccessToken(code: String) {
        addDisposable(repository.getAccessToken(code).subscribe({
            Log.d("token 받아옴", it.toString())
            accessTokenHelper.setNewToken(it)
        }, {

        }))
    }
}