package com.twaun95.data.service

import com.twaun95.domain.entity.response.ImageSearchResponse
import com.twaun95.domain.entity.response.VideoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("v2/search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ) : Response<ImageSearchResponse>

    @GET("v2/search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ) : Response<VideoSearchResponse>
}