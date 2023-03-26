package com.twaun95.domain.usecase

import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.repository.SearchRepository
import javax.inject.Inject

class DeleteStorageUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(thumbnail: Thumbnail) {
        searchRepository.deleteStorage(thumbnail)
    }
}