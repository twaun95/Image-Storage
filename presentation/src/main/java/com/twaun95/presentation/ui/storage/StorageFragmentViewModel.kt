package com.twaun95.presentation.ui.storage

import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.usecase.GetThumbnailUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageFragmentViewModel @Inject constructor(
    private val getThumbnailUseCase: GetThumbnailUseCase
) : BaseViewModel(){

    private val _thumbnailList = MutableStateFlow(emptyList<Thumbnail>())
    val thumbnailList: StateFlow<List<Thumbnail>>
        get() = _thumbnailList

    fun getThumbnailList() {
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

    fun delete(item: Thumbnail) {
        Timber.d("$item")
    }
}