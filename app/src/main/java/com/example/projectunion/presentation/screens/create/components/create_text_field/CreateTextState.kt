package com.example.projectunion.presentation.screens.create.components.create_text_field

import com.example.projectunion.common.Constants.FIELD_INVALID
import com.example.projectunion.presentation.components.fields_state.TextFieldState

class CreateTextState(): TextFieldState(
	validator = ::textValid,
	errorMessage = ::textErrorMessage
) {
}

private fun textValid(text: String) = text.isNotEmpty()

private fun textErrorMessage() = FIELD_INVALID