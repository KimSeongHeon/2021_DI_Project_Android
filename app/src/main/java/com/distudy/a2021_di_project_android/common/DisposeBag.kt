package com.distudy.a2021_di_project_android.common

import androidx.lifecycle.LifecycleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*

class DisposeBag: LifecycleObserver {
    private val DEFAULT_EXCLUSIVE_TAG = "defaultExclusive"

    private var compositeDisposable: CompositeDisposable? = null
    private var exclusiveDisposableMap: Map<String, Disposable>? = null

    private var valid = true

    init { reset() }

    private fun reset() {
        compositeDisposable = CompositeDisposable()
        exclusiveDisposableMap = HashMap()
    }

    private fun add(disposable: Disposable?) {
        if (disposable == null) {
            return
        }
        if (!isValid()) {
            disposable.dispose()
            return
        }
        compositeDisposable?.add(disposable)
    }

    fun add(vararg disposables: Disposable?) {
        for (disposable in disposables) {
            add(disposable)
        }
    }

    fun dispose() {
        if (!compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
        reset()
        valid = false
    }

    fun clear() {
        dispose()
        setValid(true)
    }

    protected fun setValid(valid: Boolean) {
        this.valid = valid
    }

    private fun isValid(): Boolean {
        return valid
    }
}