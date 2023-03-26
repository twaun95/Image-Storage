package com.twaun95.domain.entity

data class Thumbnail(
    val url: String,
    var dateTime: String,
    var isBookMarked: Boolean = false
) {
    companion object {
        fun toLocalThumbnail(thumbnail: Thumbnail) : LocalThumbnail {
            return LocalThumbnail(thumbnail.url, thumbnail.dateTime)
        }
    }
}
