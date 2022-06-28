package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.UserEdit
import com.example.projectunion.domain.repository.UserRepository

class EditProfileUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(user: UserEdit) = repository.editProfile(user)
}