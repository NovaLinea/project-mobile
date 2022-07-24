package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.model.UserEdit
import com.example.novalinea.domain.repository.UserRepository

class EditProfileUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(user: UserEdit) = repository.editProfile(user)
}