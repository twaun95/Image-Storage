package com.twaun95.domain.entity

data class KakaoImage(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String
) {
    companion object {
        fun toImageEntity(kakaoImage: KakaoImage) : ImageEntity {
            return ImageEntity(
                collection = kakaoImage.collection,
                thumbnail_url = kakaoImage.thumbnail_url,
                image_url = kakaoImage.image_url,
                width = kakaoImage.width,
                height = kakaoImage.height,
                display_sitename = kakaoImage.display_sitename,
                doc_url = kakaoImage.doc_url,
                datetime = kakaoImage.datetime
            )
        }
    }
}
