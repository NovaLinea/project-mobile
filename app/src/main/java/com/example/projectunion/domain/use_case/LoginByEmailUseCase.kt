package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.repository.AuthRepository

class LoginByEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserLogin): Boolean {
		return repository.loginByEmail(userData)
	}
}