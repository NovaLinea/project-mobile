package com.example.novalinea.presentation.components.password_field

import com.example.novalinea.common.Constants.MIN_PASSWORD_LENGTH
import com.example.novalinea.common.Constants.PASSWORD_INVALID
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class PasswordState: TextFieldState(
	validator = ::passwordValid,
	errorMessage = ::passwordErrorMessage
) {
}

private fun passwordValid(password: String) = password.length >= MIN_PASSWORD_LENGTH

private fun passwordErrorMessage() = PASSWORD_INVALID