package com.example.medoblock.domain.repository

import com.example.medoblock.domain.models.Medicine
import com.example.medoblock.domain.models.SupplyChain

interface ApiRepository {
    suspend fun getSupplyChain(addr: String): SupplyChain
    suspend fun getMedicines() : List<Medicine>
    suspend fun sendChatQuery(query: String): String
}