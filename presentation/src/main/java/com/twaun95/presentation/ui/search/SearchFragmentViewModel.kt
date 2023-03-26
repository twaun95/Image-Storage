package com.twaun95.presentation.ui.search

import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.usecase.AddStorageUseCase
import com.twaun95.domain.usecase.DeleteStorageUseCase
import com.twaun95.domain.usecase.GetStorageUseCase
import com.twaun95.domain.usecase.GetThumbnailUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getThumbnailUseCase: GetThumbnailUseCase,
    private val getStorageUseCase: GetStorageUseCase,
    private val addStorageUseCase: AddStorageUseCase,
    private val deleteStorageUseCase: DeleteStorageUseCase
) : BaseViewModel() {

    private val _thumbnailList = MutableStateFlow(emptyList<Thumbnail>())
    val thumbnailList: StateFlow<List<Thumbnail>>
        get() = _thumbnailList

    init {
        getStorage()
    }

    fun getSearchList() {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getThumbnailUseCase()
            }.onSuccess {
                _thumbnailList.value = it
                Timber.d("onSuccess: $it")
            }.onFailure {
                error.value = it.message ?: ""
                Timber.d("onFailure: $it")
            }
            stopLoading()
        }
    }

    fun getStorage() {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getStorageUseCase.invoke()
            }.onSuccess {
                Timber.d("getStorage onSuccess: $it")
            }.onFailure {
                Timber.d("getStorage onFail: $it")
            }
            stopLoading()
        }
    }

    fun saveStorage(item: Thumbnail) {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                addStorageUseCase.invoke(item)
            }.onSuccess {
                Timber.d("saveStorage onSuccess: $it")
            }.onFailure {
                Timber.d("saveStorage onFail: $it")
            }
            stopLoading()
        }
    }

    fun deleteStorage(item: Thumbnail) {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                deleteStorageUseCase.invoke(item)
            }.onSuccess {
                Timber.d("deleteStorage onSuccess: $it")
            }.onFailure {
                Timber.d("deleteStorage onFail: $it")
            }
            stopLoading()
        }
    }
}