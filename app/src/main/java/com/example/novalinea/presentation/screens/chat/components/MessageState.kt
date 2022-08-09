package com.example.novalinea.presentation.screens.chat.components

import com.example.novalinea.common.Constants.ERROR_FIELD_INVALID
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class MessageState: TextFieldState(
	validator = ::messageValid,
	errorMessage = ::messageErrorMessage
) {}

private fun messageValid(name: String) = name.isNotEmpty()

private fun messageErrorMessage() = ERROR_FIELD_INVALID