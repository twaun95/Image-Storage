package com.twaun95.data.dataSource.remote.video

import com.twaun95.data.dataSource.remote.request.SearchRequest
import com.twaun95.data.service.SearchService
import com.twaun95.domain.entity.response.VideoSearchResponse
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : VideoDataSource{
    override suspend fun getVideo(request: SearchRequest): VideoSearchResponse {
        return searchService.getVideos(
            request.query
        ).body() ?: VideoSearchResponse.empty()
    }
}