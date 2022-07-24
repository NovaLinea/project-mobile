package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.AuthRepository

class LogoutUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke() = repository.logout()
}