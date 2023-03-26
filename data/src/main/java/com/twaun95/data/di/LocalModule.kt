package com.twaun95.data.di

import android.content.Context
import com.twaun95.data.local.LocalDataSource
import com.twaun95.data.local.LocalDataSourceImpl
import com.twaun95.data.local.sharedPreference.ImageStorageSharedPreference
import com.twaun95.data.local.sharedPreference.ImageStorageSharedPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class LocalModule {

    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): ImageStorageSharedPreference {
        return ImageStorageSharedPreferenceImpl(context)
    }

    @Provides
    fun provideLocalDataSource(
        sharedPreference: ImageStorageSharedPreferenceImpl
    ): LocalDataSource {
        return LocalDataSourceImpl(sharedPreference)
    }

}