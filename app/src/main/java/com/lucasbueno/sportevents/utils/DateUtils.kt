package com.lucasbueno.sportevents.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long?.formatTimestampToTime(): String? {
    try {
        if (this == null) {
            return "00:00:00"
        }

        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            .withZone(ZoneId.systemDefault())

        return formatter.format(Instant.ofEpochSecond(this))
    } catch (error: Throwable) {
        println("Error: $error")
        return null
    }
}
