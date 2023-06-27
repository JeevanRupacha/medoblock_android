package com.example.medoblock.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val message: String? = null,
    val timeStamp: Long? = null,
    val isChatBot: Boolean = false
): Parcelable