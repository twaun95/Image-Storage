package com.twaun95.data.remote.image

import com.twaun95.data.remote.request.SearchRequest
import com.twaun95.domain.entity.response.ImageSearchResponse


interface ImageDataSource {
    suspend fun getImage(request: SearchRequest): ImageSearchResponse
}