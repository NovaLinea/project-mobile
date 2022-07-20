package com.example.projectunion.common

import java.text.SimpleDateFormat
import java.util.*

fun Long.asDate(format: String): String {
	val time = Date(this * 1000)
	val timeFormat = SimpleDateFormat(format, Locale.getDefault())
	return timeFormat.format(time)
}