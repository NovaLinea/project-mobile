package com.example.projectunion.common

import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt

fun String.asTimeLastMessage(): String {
	val lastMessageSeconds = this.toLong() / 1000
	val timeNow = System.currentTimeMillis()
	val deltaHours = (((timeNow / 1000) - lastMessageSeconds) / 3600).toDouble().roundToInt()
	val dateNow = Date().date
	val lastMessageDay = lastMessageSeconds.asDate("dd")

	return if (lastMessageDay.toInt() == dateNow && deltaHours < 24)
		this.toLong().toString().asTime()
	else if (abs(lastMessageDay.toInt() - dateNow) == 1 && deltaHours < 48)
		"вчера"
	else {
		val createdAtYear = lastMessageSeconds.asDate("yyyy")
		val nowYear = Calendar.getInstance()[1]

		if (createdAtYear.toInt() == nowYear) {
			val date = lastMessageSeconds.asDate("dd.MM").split(".")
			"${date[0]} ${date[1].toInt().asMonth()}"
		} else
			lastMessageSeconds.asDate("dd.MM.yyyy")
	}
}