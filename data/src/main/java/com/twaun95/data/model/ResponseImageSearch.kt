package com.twaun95.data.model

data class ResponseImageSearch(
    val metaData: MetaData,
    val documents: MutableList<KakaoImage>
)
