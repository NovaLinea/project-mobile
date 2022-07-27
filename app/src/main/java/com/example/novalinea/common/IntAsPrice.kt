package com.example.novalinea.common

import java.text.NumberFormat

fun Int.asPrice(): String {
	return NumberFormat
			.getIntegerInstance()
			.format(this)
			.replace(",", " ")
}