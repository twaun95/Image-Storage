package com.twaun95.domain.repository

import com.twaun95.domain.model.ImageEntity
import com.twaun95.domain.model.Result
import com.twaun95.domain.model.VideoEntity

interface SearchRepository {
    suspend fun getImages() : Result<List<ImageEntity>>
    suspend fun getVideos() : Result<List<VideoEntity>>
}