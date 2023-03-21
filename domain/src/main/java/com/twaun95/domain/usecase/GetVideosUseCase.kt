package com.twaun95.domain.usecase

import com.twaun95.domain.model.Result
import com.twaun95.domain.model.VideoEntity
import com.twaun95.domain.repository.SearchRepository
import javax.inject.Inject

class GetVideosUseCase  @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(

    ) : Result<List<VideoEntity>> {
        return searchRepository.getVideos()
    }
}