package com.example.projectunion.presentation.components.name_field

import com.example.projectunion.common.Constants.NAME_INVALID
import com.example.projectunion.presentation.components.TextFieldState

class NameState: TextFieldState(
	validator = ::nameValid,
	errorMessage = ::nameErrorMessage
) {
}

private const val maxCharName = 30

private fun nameValid(name: String) = name.length <= maxCharName && name.isNotEmpty()

private fun nameErrorMessage() = NAME_INVALID