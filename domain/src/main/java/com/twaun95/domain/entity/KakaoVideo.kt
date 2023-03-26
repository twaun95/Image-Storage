package com.twaun95.domain.entity

data class KakaoVideo(
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Int,
    val thumbnail: String,
    val author: String
)