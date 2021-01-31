package com.example.muvi.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object TimeUtil {
    private const val ONE = 1
    private const val MONTH_IN_YEAR = 12
    private const val FORMAT_DATE_TO_GET = "yyyy-MM-dd"
    private const val FORMAT_DATE_TO_SHOW = "MMMM dd"
    private const val FORMAT_DATE_TO_DDMMYYYY = "dd-MM-yyyy"

    @SuppressLint("SimpleDateFormat")
    fun getDateToShow(date: String): String {
        val result = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.US).parse(date)
        return SimpleDateFormat(FORMAT_DATE_TO_SHOW, Locale.US).format(result)
    }

    fun getCurrentDate(): String {
        val currentCalendar = Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = ((currentCalendar.get(Calendar.MONTH) + ONE) % MONTH_IN_YEAR)
        val day = currentCalendar.get(Calendar.DAY_OF_MONTH) + ONE

        val date = "$year-$month-$day"
        val result = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).parse(date)
        return SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).format(result)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDateDDMMYYY(date: String?): String {
        if (!date.isNullOrEmpty()) {
            val result = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.US).parse(date)
            return SimpleDateFormat(FORMAT_DATE_TO_DDMMYYYY, Locale.US).format(result)
        }
        return ""
    }
}
