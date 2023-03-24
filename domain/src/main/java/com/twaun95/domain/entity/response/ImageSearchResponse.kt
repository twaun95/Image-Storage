package com.twaun95.domain.entity.response

import com.twaun95.domain.entity.Meta
import com.twaun95.domain.entity.KakaoImage

data class ImageSearchResponse(
    val meta: Meta,
    val documents: MutableList<KakaoImage>
) {
    companion object {
        fun empty() = ImageSearchResponse(
            Meta(0, 0, true),
            mutableListOf()
        )
    }
}
