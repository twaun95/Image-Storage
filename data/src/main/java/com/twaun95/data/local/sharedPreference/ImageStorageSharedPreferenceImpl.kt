package com.twaun95.data.local.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.domain.key.Key
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ImageStorageSharedPreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageStorageSharedPreference {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Key.SHARED_PREFERENCE, Context.MODE_PRIVATE
    )

    // 조회
    // 최초 모든 array 조회
    override fun getArray(key: String) : ArrayList<Thumbnail> {
        val array = sharedPreferences.getString(key, null)
        return array?.let { jsonArray ->
            GsonBuilder().create().fromJson(
                jsonArray,
                object : TypeToken<ArrayList<Thumbnail>>() {}.type
            )
        } ?: ArrayList()
    }

    // 추가
    // 검색에서 선택시 추가
    override fun putArrayItem(key: String, value: Thumbnail) {
        val newArray = getArray(key).apply {
            add(value)
        }.distinct()
        val newArrayToJon = GsonBuilder().create().toJson(
            newArray,
            object : TypeToken<ArrayList<Thumbnail>>() {}.type
        )

        sharedPreferences.edit().putString(key, newArrayToJon).apply()
    }

    // 제거
    // 1. 검색 창에서 이미 선택된 것 다시 제거
    // 2. 보관함에서 선택시 제거
    override fun removeArrayItem(key: String, value: Thumbnail) {
        val newArray = getArray(key).apply {
            remove(value)
        }
        val newArrayToJon = GsonBuilder().create().toJson(
            newArray,
            object : TypeToken<ArrayList<Thumbnail>>() {}.type
        )

        sharedPreferences.edit().putString(key, newArrayToJon).apply()
    }
}