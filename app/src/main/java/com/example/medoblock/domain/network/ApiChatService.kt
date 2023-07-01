package com.example.medoblock.domain.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiChatService {

    @GET("/chat")
    suspend fun chat(
        @Query("question") question: String
    ): ChatResponse
}