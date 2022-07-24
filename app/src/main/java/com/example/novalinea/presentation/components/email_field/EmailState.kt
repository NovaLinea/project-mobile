package com.example.novalinea.presentation.components.email_field

import android.util.Patterns
import com.example.novalinea.common.Constants.EMAIL_INVALID
import com.example.novalinea.presentation.components.fields_state.TextFieldState

class EmailState: TextFieldState(
	validator = ::emailValid,
	errorMessage = ::emailErrorMessage
) {
}

private fun emailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

private fun emailErrorMessage() = EMAIL_INVALID