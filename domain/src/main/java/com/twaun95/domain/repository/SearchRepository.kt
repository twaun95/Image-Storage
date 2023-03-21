package com.twaun95.domain.repository

import com.twaun95.domain.model.ImageEntity
import com.twaun95.domain.model.Result

interface SearchRepository {
    suspend fun getImages() : Result<List<ImageEntity>>
}