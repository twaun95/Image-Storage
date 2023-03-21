package com.twaun95.data.model.image

import com.twaun95.data.model.MetaData

data class ResponseImageSearch(
    val metaData: MetaData,
    val documents: MutableList<KakaoImage>
)
