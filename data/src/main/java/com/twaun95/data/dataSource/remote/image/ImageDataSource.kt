package com.twaun95.data.dataSource.remote.image

import com.twaun95.data.dataSource.remote.request.SearchRequest
import com.twaun95.domain.entity.response.ImageSearchResponse


interface ImageDataSource {
    suspend fun getImage(request: SearchRequest): ImageSearchResponse
}