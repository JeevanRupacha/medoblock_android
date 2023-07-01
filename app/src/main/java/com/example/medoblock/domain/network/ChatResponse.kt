package com.example.medoblock.domain.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatResponse(
    @SerializedName("answer")
    val answer: String
): Parcelable