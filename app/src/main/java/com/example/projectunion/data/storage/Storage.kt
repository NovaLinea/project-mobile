package com.example.projectunion.data.storage

import android.net.Uri
import com.example.projectunion.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface Storage{

	fun addImagesProject(images: List<Uri>, uid: String): Flow<Response<MutableList<String>>>
}