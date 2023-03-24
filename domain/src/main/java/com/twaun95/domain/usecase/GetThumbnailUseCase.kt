package com.twaun95.domain.usecase

import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.repository.SearchRepository
import javax.inject.Inject


class GetThumbnailUseCase  @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
    ) : List<Thumbnail> {
        return searchRepository.getThumbnail()
    }
}