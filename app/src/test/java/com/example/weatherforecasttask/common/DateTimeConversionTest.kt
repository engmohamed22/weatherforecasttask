package com.example.weatherforecasttask.common

import org.junit.Assert.*
import org.junit.Test


class DateTimeConversionTest {

    @Test
    fun convertToTime() {
        val time=1642563464
        val actualTime="06:37 AM"
        val current=DateTimeConversion.convertToTime(time,10800)
        assertEquals(current,actualTime)
    }
}
