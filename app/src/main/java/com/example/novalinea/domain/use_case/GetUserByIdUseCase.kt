package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.UserRepository

class GetUserByIdUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(id: String) = repository.getUserById(id)
}