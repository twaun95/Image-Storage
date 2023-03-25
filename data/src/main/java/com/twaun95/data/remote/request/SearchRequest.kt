package com.twaun95.data.remote.request


data class SearchRequest(
    val query: String,
    val sort: String? = null,
    val page: Int? = null,
    val size: Int? = null
)
