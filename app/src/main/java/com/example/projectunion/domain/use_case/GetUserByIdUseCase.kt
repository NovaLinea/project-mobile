package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.UserRepository

class GetUserByIdUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(id: String) = repository.getUserById(id)
}