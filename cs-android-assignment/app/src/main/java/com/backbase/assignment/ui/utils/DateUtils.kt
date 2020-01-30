package com.backbase.assignment.ui.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val RELEASE_DATE_FORMAT = "MMMM d, yyyy"
    const val API_DATE_FORMAT = "YY-MM-DD"

    /**
     * Format String Date to String date different format
     *
     * @param inputDate        - Date string
     * @param inputDateFormat  - Given input date format
     * @param outputDateFormat - output date format
     * @return formatted date string
     */
    @SuppressLint("SimpleDateFormat")
    fun formattedDate(
        inputDate: String?,
        inputDateFormat: String?,
        outputDateFormat: String?
    ): String {
        val inputFormat = SimpleDateFormat(inputDateFormat)
        val outputFormat = SimpleDateFormat(outputDateFormat)
        var date: Date? = null
        try {
            date = inputFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputFormat.format(date)
    }
}