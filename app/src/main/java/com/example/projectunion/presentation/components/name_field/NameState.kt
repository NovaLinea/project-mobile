package com.example.projectunion.presentation.components.name_field

import com.example.projectunion.common.Constants.MAX_NAME_USER_LENGTH
import com.example.projectunion.common.Constants.NAME_INVALID
import com.example.projectunion.presentation.components.fields_state.TextFieldState

class NameState: TextFieldState(
	validator = ::nameValid,
	errorMessage = ::nameErrorMessage
) {
}

private fun nameValid(name: String) = name.length <= MAX_NAME_USER_LENGTH && name.isNotEmpty()

private fun nameErrorMessage() = NAME_INVALID