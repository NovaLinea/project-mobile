package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository

class RegisterByEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserRegister) = repository.registerByEmail(userData)
}