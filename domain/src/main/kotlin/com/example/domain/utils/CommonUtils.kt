package com.example.domain.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun String.parseDateString(): String {
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
    val newFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss")
    val zonedDateTime = ZonedDateTime.parse(this, pattern)
    return zonedDateTime.format(newFormat)
}