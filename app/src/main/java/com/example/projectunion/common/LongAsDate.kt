package com.example.projectunion.common

import java.text.SimpleDateFormat
import java.util.*

fun Long.asDate(): String {
	val time = Date(this * 1000)
	val timeFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
	return timeFormat.format(time)
}