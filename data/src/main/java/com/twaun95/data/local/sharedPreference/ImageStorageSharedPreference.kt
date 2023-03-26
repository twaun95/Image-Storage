package com.twaun95.data.local.sharedPreference

import com.twaun95.domain.entity.LocalThumbnail

interface ImageStorageSharedPreference {
    fun getArray(key: String): ArrayList<LocalThumbnail>
    fun putArrayItem(key: String, value: LocalThumbnail)
    fun removeArrayItem(key: String, value: LocalThumbnail)
}