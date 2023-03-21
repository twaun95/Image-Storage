package com.twaun95.data.repository

import com.twaun95.data.model.KakaoImage
import com.twaun95.data.service.SearchService
import com.twaun95.domain.model.ImageEntity
import com.twaun95.domain.model.Result
import com.twaun95.domain.repository.SearchRepository
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {
    override suspend fun getImages(): Result<List<ImageEntity>> {
        val response = searchService.getImages( "android")

        return try {
            if (response.isSuccessful) {
                Timber.d("taewaun ${response.body()}")
                return Result.Success(response.body()!!.documents.map { KakaoImage.toImageEntity(it) })
            } else {
                Result.Fail(IllegalArgumentException("실패."))
            }
        } catch (e: Exception) {
            Result.Fail(e)
        }
    }
}