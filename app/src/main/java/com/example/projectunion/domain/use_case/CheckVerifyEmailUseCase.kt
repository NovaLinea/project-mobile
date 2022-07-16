package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.AuthRepository

class CheckVerifyEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke() = repository.verified()
}