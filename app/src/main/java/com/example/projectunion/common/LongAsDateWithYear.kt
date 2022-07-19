package com.example.projectunion.common

import java.text.SimpleDateFormat
import java.util.*

fun Long.asDateWithYear(): String {
	val time = Date(this * 1000)
	val timeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
	return timeFormat.format(time)
}