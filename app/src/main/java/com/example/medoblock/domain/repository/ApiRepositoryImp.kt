package com.example.medoblock.domain.repository

import com.example.medoblock.domain.models.Medicine
import com.example.medoblock.domain.models.SupplyChain
import com.example.medoblock.domain.network.ApiChatService
import com.example.medoblock.domain.network.ApiService

class ApiRepositoryImp(
    private val apiService: ApiService,
    private val apiChatService: ApiChatService
) : ApiRepository {
    override suspend fun getSupplyChain(addr: String): SupplyChain {
        return apiService.getSupplyChain(addr).data ?: SupplyChain()
    }

    override suspend fun getMedicines(): List<Medicine> {
        return apiService.getMedicines().data
    }

    override suspend fun sendChatQuery(query: String): String {
        return apiChatService.chat(query).answer
    }
}