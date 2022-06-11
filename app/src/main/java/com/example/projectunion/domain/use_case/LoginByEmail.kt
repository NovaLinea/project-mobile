package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.repository.AuthRepository

class LoginByEmail(
	private val authRepository: AuthRepository
) {

	fun execute(userData: UserLogin) : Boolean {
		authRepository.loginByEmail(userData)
		return true
	}
}