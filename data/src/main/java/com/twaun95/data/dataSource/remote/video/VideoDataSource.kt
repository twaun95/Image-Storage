package com.twaun95.data.dataSource.remote.video

import com.twaun95.data.dataSource.remote.request.SearchRequest
import com.twaun95.domain.entity.response.VideoSearchResponse

interface VideoDataSource {
    suspend fun getVideo(request: SearchRequest): VideoSearchResponse
}