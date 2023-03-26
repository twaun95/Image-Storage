package com.twaun95.domain.entity

import java.text.SimpleDateFormat

data class KakaoVideo(
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Int,
    val thumbnail: String,
    val author: String
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