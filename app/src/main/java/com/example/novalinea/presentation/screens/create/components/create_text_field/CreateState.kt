package com.example.novalinea.presentation.screens.create.components.create_text_field

import com.example.novalinea.common.Constants.ERROR_FIELD_INVALID
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class CreateState(): TextFieldState(
	validator = ::textValid,
	errorMessage = ::textErrorMessage
)

private fun textValid(text: String) = text.isNotEmpty()

private fun textErrorMessage() = ERROR_FIELD_INVALID