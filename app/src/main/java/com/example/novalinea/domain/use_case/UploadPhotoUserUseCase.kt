package com.example.novalinea.domain.use_case

import android.net.Uri
import com.example.novalinea.domain.repository.UserRepository

class UploadPhotoUserUseCase(
	private val repository: UserRepository
) {

	operator fun invoke(photo: Uri, id: String) = repository.uploadPhotoUser(photo, id)
}