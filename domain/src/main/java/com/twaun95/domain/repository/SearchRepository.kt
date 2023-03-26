package com.twaun95.domain.repository

import com.twaun95.domain.entity.Thumbnail

interface SearchRepository {
    suspend fun getSearch(input: String) : List<Thumbnail>
    suspend fun getSearchNextPage() : List<Thumbnail>
    fun getStorage() : List<Thumbnail>
    fun addStorage(thumbnail: Thumbnail)
    fun deleteStorage(thumbnail: Thumbnail)
}