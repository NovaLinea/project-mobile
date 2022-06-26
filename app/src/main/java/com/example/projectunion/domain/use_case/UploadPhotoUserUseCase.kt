package com.example.projectunion.domain.use_case

import android.net.Uri
import com.example.projectunion.domain.repository.UserRepository

class UploadPhotoUserUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(photo: Uri, id: String) = repository.uploadPhotoUser(photo, id)
}