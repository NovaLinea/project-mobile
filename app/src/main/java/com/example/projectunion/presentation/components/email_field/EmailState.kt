package com.example.projectunion.presentation.components.email_field

import android.util.Patterns
import com.example.projectunion.common.Constants.EMAIL_INVALID
import com.example.projectunion.presentation.components.TextFieldState

class EmailState: TextFieldState(
	validator = ::emailValid,
	errorMessage = ::emailErrorMessage
) {
}

private fun emailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

private fun emailErrorMessage() = EMAIL_INVALID