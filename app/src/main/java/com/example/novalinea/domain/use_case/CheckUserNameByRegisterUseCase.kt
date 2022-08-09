package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.AuthRepository

class CheckUserNameByRegisterUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(username: String) = repository.checkUserName(username)
}