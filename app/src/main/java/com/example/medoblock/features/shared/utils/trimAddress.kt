package com.example.medoblock.features.shared.utils

fun trimAddress(value: String, len: Int = 6): String {
    if(value.length <= len) return value
    val lastIndex = value.length - 1
    return value.substring(0, len) + "..." + value.substring(lastIndex - len, lastIndex)
}