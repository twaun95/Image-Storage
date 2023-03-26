package com.twaun95.data.repository

import com.twaun95.data.local.LocalDataSource
import com.twaun95.data.remote.image.ImageDataSource
import com.twaun95.data.remote.request.SearchRequest
import com.twaun95.data.remote.video.VideoDataSource
import com.twaun95.domain.entity.*
import com.twaun95.domain.entity.response.VideoSearchResponse
import com.twaun95.domain.repository.SearchRepository
import timber.log.Timber
import java.text.SimpleDateFormat
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val videoDataSource: VideoDataSource,
    private val imageDataSource: ImageDataSource,
    private val localDataSource: LocalDataSource
) : SearchRepository {

    // 최초 검색
    override suspend fun getSearch(input: String): List<Thumbnail> {
        val videoResponse = videoDataSource.getVideo(SearchRequest(input))
        val imageResponse = imageDataSource.getImage(SearchRequest(input))
        val localList = localDataSource.getStorage()

        val videosToThumbnail = videoResponse.documents.map {
            Thumbnail(it.thumbnail, it.datetime,localList.contains(LocalThumbnail(it.thumbnail, it.datetime)))
        }
        val imagesToThumbnail = imageResponse.documents.map {
            Thumbnail(it.thumbnail_url, it.datetime, localList.contains(LocalThumbnail(it.thumbnail_url, it.datetime)))
        }

        val thumbnails = (videosToThumbnail + imagesToThumbnail).sortedBy {
            it.dateTime
        }.reversed()

        return thumbnails
    }

    override suspend fun getSearchNextPage(): List<Thumbnail> {
        return emptyList()
    }

    override fun getStorage(): List<Thumbnail> {
        return localDataSource.getStorage().map { Thumbnail(it.url, it.dateTime, true) }
    }

    override fun addStorage(thumbnail: Thumbnail) {
        localDataSource.saveItem(Thumbnail.toLocalThumbnail(Thumbnail(thumbnail.url, thumbnail.dateTime)))
    }

    override fun deleteStorage(thumbnail: Thumbnail) {
        localDataSource.deleteItem(Thumbnail.toLocalThumbnail(Thumbnail(thumbnail.url, thumbnail.dateTime)))
    }
}