package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.repository.AuthRepository

class LoginByEmail(
	private val repository: AuthRepository
) {

	operator fun invoke(userData: UserLogin): Boolean {
		UserRe
		repository.loginByEmail(userData)
		return true
	}
}