package com.twaun95.presentation.ui.storage

import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.usecase.DeleteStorageUseCase
import com.twaun95.domain.usecase.GetStorageUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageFragmentViewModel @Inject constructor(
    private val getStorageUseCase: GetStorageUseCase,
    private val deleteStorageUseCase: DeleteStorageUseCase
) : BaseViewModel(){

    private val _storageList = MutableStateFlow(emptyList<Thumbnail>())
    val storageList: StateFlow<List<Thumbnail>>
        get() = _storageList

    init {
        getThumbnailList()
    }

    fun getThumbnailList() {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getStorageUseCase.invoke()
            }.onSuccess {
                _storageList.value = it
                Timber.d("onSuccess: $it")
            }.onFailure {
                error.value = it.message ?: ""
                Timber.d("onFailure: $it")
            }
            stopLoading()
        }
    }

    fun delete(thumbnail: Thumbnail) {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                deleteStorageUseCase.invoke(thumbnail)
            }.onSuccess {
                getThumbnailList()
                Timber.d("onSuccess: $it")
            }.onFailure {
                error.value = it.message ?: ""
                Timber.d("onFailure: $it")
            }
            stopLoading()
        }
    }
}