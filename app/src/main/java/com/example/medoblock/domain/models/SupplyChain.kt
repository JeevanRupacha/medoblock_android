package com.example.medoblock.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupplyChain(
    @SerializedName("rawMatRequest")
    val rawMatRequest: String? = null,

    @SerializedName("rawMatAccept")
    val rawMatAccept: String? = null,

    @SerializedName("transReq")
    val transReq: String? = null,

    @SerializedName("transReqAccept")
    val transReqAccept: String? = null,

    @SerializedName("transportInit")
    val transportInit: String? = null ,

    @SerializedName("transOnWay")
    val transOnWay: String? = null,

    @SerializedName("transCompleted")
    val transCompleted: String? = null,

    @SerializedName("createMedicne")
    val createMedicne: String? = null ,

    @SerializedName("fdaReq")
    val fdaReq: String? = null,

    @SerializedName("fdaAccept")
    val fdaAccept: String? = null,

    @SerializedName("transReqMed")
    val transReqMed: String? = null,

    @SerializedName("transMedCompleted")
    val transMedCompleted: String? = null,

    @SerializedName("sellMedicine")
    val sellMedicine: String? = null
): Parcelable