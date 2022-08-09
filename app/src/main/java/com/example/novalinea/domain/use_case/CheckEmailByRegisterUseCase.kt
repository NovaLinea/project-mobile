package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.AuthRepository

class CheckEmailByRegisterUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(email: String) = repository.checkEmail(email)
}