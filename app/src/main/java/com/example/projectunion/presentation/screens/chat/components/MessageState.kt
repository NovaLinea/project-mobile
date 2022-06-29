package com.example.projectunion.presentation.screens.chat.components

import com.example.projectunion.common.Constants.FIELD_INVALID
import com.example.projectunion.presentation.components.fields_state.TextFieldState

class MessageState: TextFieldState(
	validator = ::messageValid,
	errorMessage = ::messageErrorMessage
) {}

private fun messageValid(name: String) = name.isNotEmpty()

private fun messageErrorMessage() = FIELD_INVALID