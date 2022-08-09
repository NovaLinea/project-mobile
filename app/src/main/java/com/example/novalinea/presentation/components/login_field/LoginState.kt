package com.example.novalinea.presentation.components.login_field

import com.example.novalinea.common.Constants.LOGIN_INVALID
import com.example.novalinea.common.Constants.MAX_LOGIN_USER_LENGTH
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class LoginState: TextFieldState(
	validator = ::loginValid,
	errorMessage = ::loginErrorMessage
) {
}

private fun loginValid(name: String) = name.length <= MAX_LOGIN_USER_LENGTH && name.isNotEmpty()

private fun loginErrorMessage() = LOGIN_INVALID