package com.example.medoblock.domain.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/supplychain/{address}")
    suspend fun getSupplyChain(
        @Path("address") addr: String
    ): SupplyChainResponse

    @GET("/medicines")
    suspend fun getMedicines(): MedicineResponse
}