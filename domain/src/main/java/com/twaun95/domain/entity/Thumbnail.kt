package com.twaun95.domain.entity

data class Thumbnail(
    val url: String,
    val dateTime: String,
    val isBookMarked: Boolean = false
)
