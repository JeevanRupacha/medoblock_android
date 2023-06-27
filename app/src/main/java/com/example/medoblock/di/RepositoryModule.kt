package com.example.medoblock.di

import com.example.medoblock.domain.network.ApiService
import com.example.medoblock.domain.repository.ApiRepository
import com.example.medoblock.domain.repository.ApiRepositoy_imp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.PrimitiveIterator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {

    @Singleton
    @Provides
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository{
        return ApiRepositoy_imp(apiService = apiService)
    }
}