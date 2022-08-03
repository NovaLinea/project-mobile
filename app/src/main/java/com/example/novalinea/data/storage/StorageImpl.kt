package com.example.novalinea.data.storage

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.example.novalinea.common.Constants.PATH_IMAGES_PROJECTS
import com.example.novalinea.common.Constants.PATH_IMAGES_USERS
import com.example.novalinea.domain.model.Response
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class StorageImpl(
	private val storage: StorageReference,
	private val context: Context
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

			val compressPhoto = compressBitmap(photo, 50)
			val fileRef = storage.child("$PATH_IMAGES_USERS/$id")
			fileRef.putBytes(compressPhoto).await()
			val url = fileRef.downloadUrl.await()

			emit(Response.Success(url.toString()))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	private fun compressBitmap(photoUri: Uri, quality: Int): ByteArray {
		//val bitmap = Bitmap.createBitmap(photo.width, photo.height, Bitmap.Config.ARGB_8888)
		//val pfd = context.contentResolver.openFileDescriptor(photoUri, "r")
		//val photoBitmap = BitmapFactory.decodeFileDescriptor(pfd?.fileDescriptor, null, null)
		val photoBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, photoUri)
		val stream = ByteArrayOutputStream()
		photoBitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
		return stream.toByteArray()
	}

	override fun deletePhotoUser(id: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val fileRef = storage.child("$PATH_IMAGES_USERS/$id")
			fileRef.delete()

			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}