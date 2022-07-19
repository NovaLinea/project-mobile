package com.example.projectunion.common

import java.text.SimpleDateFormat
import java.util.*

fun Long.asYear(): String {
	val time = Date(this * 1000)
	val timeFormat = SimpleDateFormat("yyyy", Locale.getDefault())
	return timeFormat.format(time)
}