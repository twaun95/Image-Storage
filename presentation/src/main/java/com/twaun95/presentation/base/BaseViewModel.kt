package com.twaun95.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    val error = MutableLiveData<String>()

    protected fun startLoading() { _loading.value = true }
    protected fun stopLoading() { _loading.value = false }
}