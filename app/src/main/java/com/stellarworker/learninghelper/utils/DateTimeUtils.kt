package com.stellarworker.learninghelper.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long, pattern: String): String {
    val date = Date(time)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

fun getCurrentTime(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String, pattern: String): Long {
    val dateFormat = SimpleDateFormat(pattern)
    return dateFormat.parse(date)?.time ?: Long.ZERO
}





















