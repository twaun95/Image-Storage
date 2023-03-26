package com.twaun95.domain.repository

import com.twaun95.domain.entity.ImageEntity
import com.twaun95.domain.entity.Result
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.entity.VideoEntity
import com.twaun95.domain.entity.response.VideoSearchResponse

interface SearchRepository {
    suspend fun getThumbnail() : List<Thumbnail>
    fun getStorage() : List<Thumbnail>
    fun addStorage(thumbnail: Thumbnail)
    fun removeStorage(thumbnail: Thumbnail)
}