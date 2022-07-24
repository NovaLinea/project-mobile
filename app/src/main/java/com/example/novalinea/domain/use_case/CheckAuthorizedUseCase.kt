package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.AuthRepository

class CheckAuthorizedUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke() = repository.authorized()
}