package com.example.novalinea.data.storage

import android.net.Uri
import com.example.novalinea.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface Storage{

	fun addImagesProject(images: List<ByteArray>, id: String): Flow<Response<MutableList<String>>>
	fun addPhotoUser(photo: Uri, id: String): Flow<Response<String>>
	fun deletePhotoUser(id: String): Flow<Response<Boolean>>
}