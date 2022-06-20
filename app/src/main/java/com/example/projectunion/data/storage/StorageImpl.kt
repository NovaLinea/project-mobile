package com.example.projectunion.data.storage

import android.net.Uri
import com.example.projectunion.common.Constants.IMAGES_PROJECTS
import com.example.projectunion.domain.model.Response
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class StorageImpl(
	private val storage: StorageReference
): Storage {

	override fun addPhotoProject(imageUri: Uri, uid: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val fileRef = storage.child("$IMAGES_PROJECTS/$uid")
			fileRef.putFile(imageUri).await()

			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}