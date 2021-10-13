package com.example.newsapp.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun dateFormatTime(oldStringDate: String?): String? {
    val newDate: String?
    val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}

private fun getCountry(): String =
    Locale.getDefault().country.lowercase(Locale.ROOT)