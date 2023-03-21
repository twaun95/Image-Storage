package com.twaun95.data.di

import com.twaun95.data.repository.SearchRepositoryImpl
import com.twaun95.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(repositoryImpl: SearchRepositoryImpl): SearchRepository {
        return repositoryImpl
    }
}