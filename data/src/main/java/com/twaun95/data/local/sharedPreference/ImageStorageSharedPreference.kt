package com.twaun95.data.local.sharedPreference

import com.twaun95.domain.entity.Thumbnail

interface ImageStorageSharedPreference {
    fun getArray(key: String): ArrayList<Thumbnail>
    fun putArrayItem(key: String, value: Thumbnail)
    fun removeArrayItem(key: String, value: Thumbnail)
}