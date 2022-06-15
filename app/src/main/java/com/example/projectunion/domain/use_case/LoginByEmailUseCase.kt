package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.repository.AuthRepository

class LoginByEmailUseCase(
	private val repository: AuthRepository
) {

	suspend operator fun invoke(userData: UserLogin) = repository.loginByEmail(userData)
}