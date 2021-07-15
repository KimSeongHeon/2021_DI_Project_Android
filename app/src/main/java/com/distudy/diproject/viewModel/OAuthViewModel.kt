package com.distudy.diproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.distudy.diproject.common.AccessTokenController
import com.distudy.diproject.common.BaseViewModel
import com.distudy.diproject.data.AccessToken
import com.distudy.diproject.repository.Repository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OAuthViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    @Inject
    lateinit var accessTokenHelper: AccessTokenController

    private var _tokenLiveData = MutableLiveData<String>()
    val tokenLiveData: LiveData<String>
        get() = _tokenLiveData

    fun getAccessToken(code: String) {
        addDisposable(
            //SubscribeOn : 구독에 사용할 쓰레드를 지정
            //ObserveOn : Observable이 다음처리를 진행할 때 사용할 스레드를 지정
            repository.getAccessToken(code)
                .observeOn(Schedulers.io())
                .subscribe({
                    saveCurrentToken(token = it)
                }, {
                })
        )
    }

    fun saveCurrentToken(token: AccessToken) {
        accessTokenHelper.currentToken = token
    }

    fun setTokenData(token: String) {
        _tokenLiveData.postValue(token)
    }
}