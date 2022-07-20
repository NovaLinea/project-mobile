package com.example.projectunion.data.storage

import android.net.Uri
import com.example.projectunion.common.Constants.PATH_IMAGES_PROJECTS
import com.example.projectunion.common.Constants.PATH_IMAGES_USERS
import com.example.projectunion.domain.model.Response
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class StorageImpl(
	private val storage: StorageReference
): Storage {

	override fun addImagesProject(images: List<Uri>, id: String) = flow<Response<MutableList<String>>> {
		try {
			emit(Response.Loading)
			val imagesUrl = mutableListOf<String>()

			images.forEachIndexed { index, imageUri ->
				val fileRef = storage.child("$PATH_IMAGES_PROJECTS/$id/image_${index}")
				fileRef.putFile(imageUri).await()
				val url = fileRef.downloadUrl.await()
				imagesUrl.add(index, url.toString())
			}

			emit(Response.Success(imagesUrl))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun addPhotoUser(photo: Uri, id: String) = flow<Response<String>> {
		try {
			emit(Response.Loading)

			val fileRef = storage.child("$PATH_IMAGES_USERS/$id")
			fileRef.putFile(photo).await()
			val url = fileRef.downloadUrl.await()

			emit(Response.Success(url.toString()))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}