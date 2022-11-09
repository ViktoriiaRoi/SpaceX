package com.viktoriiaroi.spacex.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private val timeFormatter = SimpleDateFormat("hh:mm", Locale.ENGLISH)

    fun timestampToDate(timestamp: Long): Pair<String, String> {
        val date = Date(timestamp * 1000)
        return Pair(dateFormatter.format(date), timeFormatter.format(date))
    }
}