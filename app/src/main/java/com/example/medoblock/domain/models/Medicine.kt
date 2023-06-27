package com.example.medoblock.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("manuId")
    val manuId: String?,

    @SerializedName("manuDate")
    val manuDate: String?,

    @SerializedName("expDate")
    val expDate: String?,

    @SerializedName("fdaStatus")
    val fdaStatus: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("count")
    val count: Long?,

    @SerializedName("medSupplyChainAddr")
    val medSupplyChainAddr: String?
): Parcelable