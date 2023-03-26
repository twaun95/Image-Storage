package com.twaun95.data.di

import com.twaun95.data.remote.image.ImageDataSource
import com.twaun95.data.remote.image.ImageDataSourceImpl
import com.twaun95.data.remote.video.VideoDataSource
import com.twaun95.data.remote.video.VideoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteModule {

    @Binds
    @Singleton
    abstract fun bindVideoDataSource(
        dataSource: VideoDataSourceImpl
    ) : VideoDataSource

    @Binds
    @Singleton
    abstract fun bindImageDataSource(
        dataSource: ImageDataSourceImpl
    ) : ImageDataSource
}