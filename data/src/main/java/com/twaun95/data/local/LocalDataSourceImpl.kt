package com.twaun95.data.local

import com.twaun95.data.local.sharedPreference.ImageStorageSharedPreference
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.key.Key
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val imageStorageSharedPreference: ImageStorageSharedPreference
) : LocalDataSource {
    override fun getStorage(): ArrayList<Thumbnail> {
        return imageStorageSharedPreference.getArray(Key.SHARED_PREFERENCE)
    }

    override fun deleteItem(item: Thumbnail) {
        imageStorageSharedPreference.removeArrayItem(Key.SHARED_PREFERENCE, item)
    }

    override fun saveItem(item: Thumbnail) {
        imageStorageSharedPreference.putArrayItem(Key.SHARED_PREFERENCE, item)
    }
}