package com.example.medoblock.features.shared.utils

import java.text.SimpleDateFormat
import java.util.Date

object MDateTime {
    fun timestampToDate(timestamp: Long?): String {
        timestamp ?: return ""
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(timestamp)
        return sdf.format(date)
    }

    fun timestampToTime(timestamp: Long?): String {
        timestamp ?: return  ""
        val sdf = SimpleDateFormat("HH:mm")
        val date = Date(timestamp)
        return sdf.format(date)
    }
}