package com.example.projectunion.presentation.screens.login

data class LoginState(
	val error: String = "",
	val isLoading: Boolean = false,
	val isAuth: Boolean = false,
)