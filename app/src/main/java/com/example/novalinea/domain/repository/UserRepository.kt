package com.example.novalinea.domain.repository

import android.net.Uri
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserEdit
import com.example.novalinea.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

	fun getUserById(id: String): Flow<Response<UserProfile?>>
	fun uploadPhotoUser(photo: Uri, id: String): Flow<Response<String>>
	fun editProfile(user: UserEdit): Flow<Response<Boolean>>
}