package com.example.projectunion.domain.repository

import android.net.Uri
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserEdit
import com.example.projectunion.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

	fun getUserById(id: String): Flow<Response<UserProfile?>>
	fun uploadPhotoUser(photo: Uri, id: String): Flow<Response<String>>
	fun editProfile(user: UserEdit): Flow<Response<Boolean>>
}