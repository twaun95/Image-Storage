package com.twaun95.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.usecase.AddStorageUseCase
import com.twaun95.domain.usecase.DeleteStorageUseCase
import com.twaun95.domain.usecase.GetSearchUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getThumbnailUseCase: GetSearchUseCase,
    private val addStorageUseCase: AddStorageUseCase,
    private val deleteStorageUseCase: DeleteStorageUseCase
) : BaseViewModel() {

    private val _thumbnailList = MutableStateFlow(emptyList<Thumbnail>())
    val thumbnailList: StateFlow<List<Thumbnail>> get() = _thumbnailList

    val searchText = MutableLiveData<String>("")

    init {}

    // 현재까지의 리스트 불러오기
    fun getSearchList() {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getThumbnailUseCase(searchText.value.toString())
            }.onSuccess {
                _thumbnailList.value = it
            }.onFailure {
                error.value = it.message ?: ""
            }
            stopLoading()
        }
    }

    // 다음 페이지 불러오기
    fun getSearchNextPage() {

    }

    fun saveStorage(item: Thumbnail) {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                addStorageUseCase.invoke(item)
            }.onSuccess {
            }.onFailure {
                error.value = it.message ?: ""
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

            }.onFailure {
                error.value = it.message ?: ""
            }
            stopLoading()
        }
    }
}