package com.twaun95.data.local

import com.twaun95.domain.entity.Thumbnail

interface LocalDataSource {
    fun getStorage(): ArrayList<Thumbnail>
    fun deleteItem(item: Thumbnail)
    fun saveItem(item: Thumbnail)
}