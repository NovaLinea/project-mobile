package com.example.projectunion.common

import com.google.firebase.Timestamp
import java.util.*
import kotlin.math.roundToInt

fun Timestamp.asCreatedAt(): String {
	val timeNow = System.currentTimeMillis()
	val deltaSeconds = (timeNow / 1000) - this.seconds

	if (deltaSeconds < 60)
		return "$deltaSeconds сек"
	else {
		val deltaMinutes = (deltaSeconds / 60).toDouble().roundToInt()

		if (deltaMinutes < 60)
			return "$deltaMinutes мин"
		else {
			val deltaHours = (deltaMinutes / 60).toDouble().roundToInt()

			when {
				deltaHours < 24 -> {
					return when (deltaHours) {
						1, 21 -> "$deltaHours час"
						2, 3, 4, 22, 23 -> "$deltaHours часа"
						else -> "$deltaHours часов"
					}
				}
				deltaHours < 48 -> return "вчера"
				else -> {
					val createdAtYear = this.seconds.asYear()
					val nowYear = Calendar.getInstance()[1]

					return if (createdAtYear.toInt() == nowYear) {
						val date = this.seconds.asDate().split(".")
						"${date[0]} ${date[1].toInt().asMonth()}"
					} else
						this.seconds.asDateWithYear()
				}
			}
		}
	}
}