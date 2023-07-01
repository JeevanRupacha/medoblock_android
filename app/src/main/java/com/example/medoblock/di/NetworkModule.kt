package com.example.medoblock.di

import com.example.medoblock.core.utils.Config.BASE_URI
import com.example.medoblock.core.utils.Config.CHAT_BASE_URI
import com.example.medoblock.domain.network.ApiChatService
import com.example.medoblock.domain.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerApiService(): ApiService {
        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(1, TimeUnit.HOURS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providerApiChatService(): ApiChatService {
        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(1, TimeUnit.HOURS)
            .build()

        return Retrofit.Builder()
            .baseUrl(CHAT_BASE_URI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiChatService::class.java)
    }
}