package com.viktoriiaroi.spacex.utils

import com.viktoriiaroi.core.model.DatePrecision
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private val timeFormatter = SimpleDateFormat("hh:mm", Locale.ENGLISH)

    private val dayDateFormatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    private val monthDateFormatter = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val yearDateFormatter = SimpleDateFormat("yyyy", Locale.ENGLISH)
    private val precisionTimeFormatter = SimpleDateFormat("hh:00 a z", Locale.ENGLISH)

    fun getDate(timestamp: Int) = Date(timestamp.toLong() * 1000)

    fun unixToDate(timestamp: Int): String = dateFormatter.format(getDate(timestamp))

    fun unixToTime(timestamp: Int): String = timeFormatter.format(getDate(timestamp))

    fun unixToPrecisionDate(timestamp: Int?, precision: DatePrecision): String =
        timestamp?.let {
            val formatter = when (precision) {
                DatePrecision.DAY, DatePrecision.HOUR -> dayDateFormatter
                DatePrecision.MONTH -> monthDateFormatter
                else -> yearDateFormatter
            }
            formatter.format(getDate(timestamp))
        } ?: "Pending"

    fun unixToPrecisionTime(timestamp: Int?, precision: DatePrecision): String =
        if (timestamp != null && precision == DatePrecision.HOUR) {
            precisionTimeFormatter.format(getDate(timestamp))
        } else "Pending"
}