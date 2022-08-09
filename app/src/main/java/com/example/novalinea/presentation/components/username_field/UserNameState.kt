package com.example.novalinea.presentation.components.username_field

import com.example.novalinea.common.Constants.ERROR_FIELD_USERNAME_INVALID
import com.example.novalinea.common.Constants.MAX_LOGIN_USER_LENGTH
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class UserNameState: TextFieldState(
	validator = ::userNameValid,
	errorMessage = ::userNameErrorMessage
) {
}

private fun userNameValid(name: String) = name.length <= MAX_LOGIN_USER_LENGTH && name.isNotEmpty()

private fun userNameErrorMessage() = ERROR_FIELD_USERNAME_INVALID