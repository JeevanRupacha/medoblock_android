package com.example.medoblock.domain.network

import android.os.Parcelable
import com.example.medoblock.domain.models.Medicine
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicineResponse(
    @SerializedName("data")
    val data: List<Medicine> = emptyList()
): Parcelable