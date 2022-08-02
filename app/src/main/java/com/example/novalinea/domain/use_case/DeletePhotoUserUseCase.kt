package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.UserRepository

class DeletePhotoUserUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(id: String) = repository.deletePhotoUser(id)
}