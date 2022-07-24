package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.UserLogin
import com.example.novalinea.domain.repository.AuthRepository

class LoginByEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserLogin) = repository.loginByEmail(userData)
}