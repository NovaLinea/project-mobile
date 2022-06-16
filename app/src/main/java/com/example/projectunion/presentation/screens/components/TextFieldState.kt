package com.example.projectunion.presentation.screens.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

open class TextFieldState(
	private val validator: (String) -> Boolean = { true },
	private val errorMessage: () -> String
) {

	var text by mutableStateOf("")
	var error by mutableStateOf<String?>(null)

	fun validate() {
		error = if (isValidText()) {
			null
		}
		else {
			errorMessage()
		}
	}

	fun isValidText() = validator(text)
}