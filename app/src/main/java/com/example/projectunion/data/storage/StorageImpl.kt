package com.example.projectunion.data.storage

import android.net.Uri
import android.util.Log
import com.example.projectunion.common.Constants.IMAGES_PROJECTS
import com.example.projectunion.common.Constants.TAG
import com.example.projectunion.domain.model.Response
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class StorageImpl(
	private val storage: StorageReference
): Storage {

	override fun addImagesProject(images: List<Uri>, uid: String) = flow<Response<MutableList<String>>> {
		try {
			emit(Response.Loading)
			var imagesUrl = mutableListOf<String>()

			images.forEachIndexed { index, imageUri ->
				val fileRef = storage.child("$IMAGES_PROJECTS/$uid/image_${index}")
				fileRef.putFile(imageUri).await()
				val url = fileRef.downloadUrl.await()
				imagesUrl.add(index, url.toString())
			}

			emit(Response.Success(imagesUrl))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}