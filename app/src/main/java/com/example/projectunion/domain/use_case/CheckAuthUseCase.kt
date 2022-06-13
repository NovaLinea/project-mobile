package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.UserRepository

class CheckAuthUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(id: String): Boolean {
		return repository.checkAuth(id)
	}
}