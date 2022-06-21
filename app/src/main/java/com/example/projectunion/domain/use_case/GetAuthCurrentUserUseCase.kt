package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.AuthRepository

class GetAuthCurrentUserUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke() = repository.getAuthCurrentUser()
}