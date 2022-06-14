package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.repository.AuthRepository
import javax.inject.Inject

class LoginByEmailUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserLogin) = repository.loginByEmail(userData)
}