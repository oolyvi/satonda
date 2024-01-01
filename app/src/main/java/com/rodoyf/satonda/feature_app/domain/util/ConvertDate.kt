package com.rodoyf.satonda.feature_app.domain.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyyy | HH:mm")  // yyyy.MM.dd HH:mm
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun convertLongToTimeForTask(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun convertLongToTimeInHourForTask(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}