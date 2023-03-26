package com.twaun95.data.repository

import com.twaun95.data.local.LocalDataSource
import com.twaun95.data.remote.image.ImageDataSource
import com.twaun95.data.remote.request.SearchRequest
import com.twaun95.data.remote.video.VideoDataSource
import com.twaun95.domain.entity.*
import com.twaun95.domain.repository.SearchRepository
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val videoDataSource: VideoDataSource,
    private val imageDataSource: ImageDataSource,
    private val localDataSource: LocalDataSource
) : SearchRepository {
    override suspend fun getThumbnail(): List<Thumbnail> {
        val videoResponse = videoDataSource.getVideo(SearchRequest("android"))
        val imageResponse = imageDataSource.getImage(SearchRequest("android"))

        val videosToThumbnail = videoResponse.documents.map {
            Thumbnail(it.thumbnail, it.datetime)
        }
        val imagesToThumbnail = imageResponse.documents.map {
            Thumbnail(it.thumbnail_url, it.datetime)
        }

        val thumbnails = (videosToThumbnail + imagesToThumbnail).sortedBy {
            it.dateTime
        }.reversed()

//        Timber.d("videoResponse: ${videoResponse.meta}")
//        Timber.d("videosToThumbnail: ${videosToThumbnail.size}")
//        Timber.d("imageResponse: ${imageResponse.meta}")
//        Timber.d("imagesToThumbnail: ${imagesToThumbnail.size}")

        Timber.d("thumbnails: ${thumbnails.size}")
        Timber.d("storage: ${localDataSource.getStorage()}")

        return thumbnails
    }

    override fun getStorage(): List<Thumbnail> {
        return localDataSource.getStorage().toList()
    }

    override fun addStorage(thumbnail: Thumbnail) {
        localDataSource.saveItem(thumbnail)
    }

    override fun removeStorage(thumbnail: Thumbnail) {
        localDataSource.deleteItem(thumbnail)
    }
}