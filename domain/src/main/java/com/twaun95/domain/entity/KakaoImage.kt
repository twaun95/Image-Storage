package com.twaun95.domain.entity

import java.text.SimpleDateFormat

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
        fun toThumbnail(url: String, dateTime: String, bookMarked: Boolean) : Thumbnail {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("yyyy년MM월dd일 HH시mm분")

            return Thumbnail(url, formatter.format(parser.parse(dateTime)), bookMarked)
        }

        fun toLocalThumbnail(url: String, dateTime: String) : LocalThumbnail {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("yyyy년MM월dd일 HH시mm분")

            return LocalThumbnail(url, formatter.format(parser.parse(dateTime)))
        }
    }
}