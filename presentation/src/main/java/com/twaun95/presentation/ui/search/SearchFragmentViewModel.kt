package com.twaun95.presentation.ui.search

import androidx.lifecycle.viewModelScope
import com.twaun95.domain.model.ImageEntity
import com.twaun95.domain.model.Result
import com.twaun95.domain.model.VideoEntity
import com.twaun95.domain.usecase.GetSearchUseCase
import com.twaun95.domain.usecase.GetVideosUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
    private val getVideosUseCase: GetVideosUseCase
) : BaseViewModel() {

    private val _imageList = MutableStateFlow(emptyList<ImageEntity>())
    val imageList: StateFlow<List<ImageEntity>>
        get() = _imageList

    private val _videoList = MutableStateFlow(emptyList<VideoEntity>())
    val videoList: StateFlow<List<VideoEntity>>
        get() = _videoList

    fun getSearchList() {
        viewModelScope.launch {
            startLoading()
            val result = getSearchUseCase()
            when(result) {
                is Result.Success ->{
                    _imageList.value = result.data
                    Timber.d("viewModel success${result.data}")
                }
                is Result.Fail -> { Timber.d("viewModel fail${result.exception}") }
            }
            stopLoading()
        }
    }

    fun getVideoList() {
        viewModelScope.launch {
            startLoading()
            val result = getVideosUseCase()
            when(result) {
                is Result.Success ->{
                    _videoList.value = result.data
                    Timber.d("viewModel success${result.data}")
                }
                is Result.Fail -> { Timber.d("viewModel fail${result.exception}") }
            }
            stopLoading()
        }
    }

    fun zipTest() {
        viewModelScope.launch {
            val newFlow = _imageList.zip(_videoList) { images, videos ->

            }
        }
    }
}