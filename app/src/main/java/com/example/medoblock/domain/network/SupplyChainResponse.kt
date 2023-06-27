package com.example.medoblock.domain.network

import android.os.Parcelable
import com.example.medoblock.domain.models.SupplyChain
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupplyChainResponse(
    @SerializedName("data")
    val data : SupplyChain?
): Parcelable