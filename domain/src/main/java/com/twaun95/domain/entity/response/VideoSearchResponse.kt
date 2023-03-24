package com.twaun95.domain.entity.response

import com.twaun95.domain.entity.Meta
import com.twaun95.domain.entity.KakaoVideo

data class VideoSearchResponse(
    val meta: Meta,
    val documents: MutableList<KakaoVideo>
) {
    companion object {
        fun empty() = VideoSearchResponse(
            Meta(0, 0, true),
            mutableListOf()
        )
    }
}
