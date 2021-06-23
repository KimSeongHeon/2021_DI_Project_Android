package com.distudy.diproject.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel: ViewModel() {
    private val disposeBag = DisposeBag()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    protected fun addDisposable(disposable: Disposable?) {
        if(disposable == null) return
        disposeBag.add(disposable)
    }
}