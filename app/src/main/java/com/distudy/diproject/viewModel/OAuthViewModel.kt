package com.distudy.diproject.viewModel

import android.util.Log
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.common.BaseViewModel
import com.distudy.diproject.repository.Repository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OAuthViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    @Inject
    lateinit var accessTokenHelper: AccessTokenController

    fun getAccessToken(code: String) {
        addDisposable(repository.getAccessToken(code).observeOn(Schedulers.io()).subscribe({
            Log.d("token 받아옴", it.toString())
            accessTokenHelper.currentToken = it
        }, {

        }))
    }
}