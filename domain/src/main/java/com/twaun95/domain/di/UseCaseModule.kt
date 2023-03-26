package com.twaun95.domain.di

import com.twaun95.domain.repository.SearchRepository
import com.twaun95.domain.usecase.AddStorageUseCase
import com.twaun95.domain.usecase.DeleteStorageUseCase
import com.twaun95.domain.usecase.GetStorageUseCase
import com.twaun95.domain.usecase.GetThumbnailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetThumbnail(repository: SearchRepository): GetThumbnailUseCase {
        return GetThumbnailUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetStorage(repository: SearchRepository): GetStorageUseCase {
        return GetStorageUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddStorage(repository: SearchRepository): AddStorageUseCase {
        return AddStorageUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteStorage(repository: SearchRepository): DeleteStorageUseCase {
        return DeleteStorageUseCase(repository)
    }
}