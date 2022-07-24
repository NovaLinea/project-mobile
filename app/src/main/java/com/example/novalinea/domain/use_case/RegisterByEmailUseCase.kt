package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.UserRegister
import com.example.novalinea.domain.repository.AuthRepository

class RegisterByEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserRegister) = repository.registerByEmail(userData)
}