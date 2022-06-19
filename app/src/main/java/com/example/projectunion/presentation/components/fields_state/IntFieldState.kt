package com.example.projectunion.presentation.components.fields_state

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

open class IntFieldState(
	private val validator: (Int) -> Boolean = { true },
	private val errorMessage: () -> String
) {

	var value by mutableStateOf(0)
	var error by mutableStateOf<String?>(null)

	fun validate() {
		error = if (isValidText()) {
			null
		}
		else {
			errorMessage()
		}
	}

	fun isValidText() = validator(value)
}