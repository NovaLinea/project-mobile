package com.example.novalinea.common

import java.text.NumberFormat

fun Int.asViews(): String {
	val views = NumberFormat
			.getIntegerInstance()
			.format(this)
			.replace(",", " ")

	return when(views.last()) {
		'1' -> "$views просмотр"
		'2', '3', '4' -> "$views просмотра"
		else -> "$views просмотров"
	}
}