package com.twaun95.data.model.video

import com.twaun95.data.model.MetaData

data class ResponseVideo(
    val metaData: MetaData,
    val documents: MutableList<KakaoVideo>
)
