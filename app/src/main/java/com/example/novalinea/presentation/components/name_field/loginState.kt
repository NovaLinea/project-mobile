package com.example.novalinea.presentation.components.name_field

import com.example.novalinea.common.Constants.MAX_NAME_USER_LENGTH
import com.example.novalinea.common.Constants.NAME_INVALID
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class NameState: TextFieldState(
	validator = ::nameValid,
	errorMessage = ::nameErrorMessage
) {
}

private fun nameValid(name: String) = name.length <= MAX_NAME_USER_LENGTH && name.isNotEmpty()

private fun nameErrorMessage() = NAME_INVALID