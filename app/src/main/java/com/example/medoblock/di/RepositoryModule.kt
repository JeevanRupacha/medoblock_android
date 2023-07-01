package com.example.medoblock.di

import com.example.medoblock.domain.network.ApiChatService
import com.example.medoblock.domain.network.ApiService
import com.example.medoblock.domain.repository.ApiRepository
import com.example.medoblock.domain.repository.ApiRepositoryImp
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
    fun provideApiRepository(
        apiService: ApiService,
        apiChatService: ApiChatService
    ): ApiRepository{
        return ApiRepositoryImp(apiService = apiService, apiChatService = apiChatService)
    }
}