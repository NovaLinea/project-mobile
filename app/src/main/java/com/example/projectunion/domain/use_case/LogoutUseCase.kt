package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.AuthRepository

class LogoutUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke() = repository.logout()
}