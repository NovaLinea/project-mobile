package com.example.projectunion.presentation.screens.auth.components.password_field

import com.example.projectunion.common.Constants.PASSWORD_INVALID
import com.example.projectunion.presentation.screens.auth.components.TextFieldState

class PasswordState: TextFieldState(
	validator = ::passwordValid,
	errorMessage = ::passwordErrorMessage
) {
}

private const val minCharPassword = 6

private fun passwordValid(password: String) = password.length >= minCharPassword

private fun passwordErrorMessage() = PASSWORD_INVALID