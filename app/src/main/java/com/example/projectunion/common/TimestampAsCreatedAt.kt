package com.example.projectunion.common

import android.util.Log
import com.example.projectunion.common.Constants.TAG
import com.google.firebase.Timestamp
import java.util.*
import kotlin.math.abs
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

			if (deltaHours < 24) {
				return when (deltaHours) {
					1, 21 -> "$deltaHours час"
					2, 3, 4, 22, 23 -> "$deltaHours часа"
					else -> "$deltaHours часов"
				}
			}
			else {
				val createdAtDay = this.seconds.asDate("dd")

				return if (abs(createdAtDay.toInt() - Date().date) == 1 && deltaHours < 48)
					"вчера"
				else {
					val createdAtYear = this.seconds.asDate("yyyy")
					val nowYear = Calendar.getInstance()[1]

					if (createdAtYear.toInt() == nowYear) {
						val date = this.seconds.asDate("dd.MM").split(".")
						"${date[0]} ${date[1].toInt().asMonth()}"
					} else
						this.seconds.asDate("dd.MM.yyyy")
				}
			}
		}
	}
}