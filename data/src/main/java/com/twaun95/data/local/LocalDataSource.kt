package com.twaun95.data.local

import com.twaun95.domain.entity.LocalThumbnail

interface LocalDataSource {
    fun getStorage(): List<LocalThumbnail>
    fun deleteItem(item: LocalThumbnail)
    fun saveItem(item: LocalThumbnail)
}