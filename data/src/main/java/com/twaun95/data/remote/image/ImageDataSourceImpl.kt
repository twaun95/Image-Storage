package com.twaun95.data.remote.image

import com.twaun95.data.remote.request.SearchRequest
import com.twaun95.data.service.SearchService
import com.twaun95.domain.entity.response.ImageSearchResponse
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : ImageDataSource {
    override suspend fun getImage(request: SearchRequest): ImageSearchResponse {
        return searchService.getImages(
            request.query
        ).body() ?: ImageSearchResponse.empty()
    }
}