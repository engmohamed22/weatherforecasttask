package com.example.weatherforecasttask.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConversion {
    private const val TIME_FORMAT = "hh:mm aaa"
    private const val DATE_TIME_FORMAT = "EEE dd MMM yyyy hh:mm aaa"

    fun convertToTime(milliseconds: Int, timeZoneOffset: Int): String {
        val time: Long = (milliseconds.toLong() * 1000L) + (timeZoneOffset.toLong() * 1000)
        val date = Date(time)
        val timeZone = TimeZone.getDefault()
        timeZone.rawOffset = timeZoneOffset
        val formatter: DateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(date)
    }

    fun convertToDateTime(milliseconds: Int, timeZoneOffset: Int): String {
        val time: Long = (milliseconds.toLong() * 1000L) + (timeZoneOffset.toLong() * 1000)
        val date = Date(time)
        val timeZone = TimeZone.getDefault()
        timeZone.rawOffset = timeZoneOffset
        val formatter: DateFormat = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(date)
    }
}