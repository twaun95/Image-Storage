package com.twaun95.domain.di

import com.twaun95.domain.repository.SearchRepository
import com.twaun95.domain.usecase.GetSearchUseCase
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
    fun provideGetSearch(repository: SearchRepository): GetSearchUseCase {
        return GetSearchUseCase(repository)
    }
}