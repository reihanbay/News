package com.submission.news.utils.helper

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun formatDate(sourceDate: String, sourcePattern: String, expectedFormat: String, locale: String = "id"): String {
        val sourceFormat = SimpleDateFormat(sourcePattern)
        val parsedDate: Date = sourceFormat.parse(sourceDate)
        val expected = SimpleDateFormat(expectedFormat, Locale(locale))
        return expected.format(parsedDate)
    }
}