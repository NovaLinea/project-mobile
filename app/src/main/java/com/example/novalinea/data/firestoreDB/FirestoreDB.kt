package com.example.novalinea.data.firestoreDB

import com.example.novalinea.domain.model.*
import kotlinx.coroutines.flow.Flow

interface FirestoreDB{

	// User
	fun checkEmail(email: String): Flow<Response<Boolean>>
	fun checkUserName(username: String): Flow<Response<Boolean>>
	fun createUser(userData: UserRegister, uid: String): Flow<Response<Boolean>>
	fun getUserById(id: String): Flow<Response<UserProfile?>>
	fun uploadPhotoUser(photo: String, id: String): Flow<Response<String>>
	fun deletePhotoUser(id: String): Flow<Response<Boolean>>
	fun editProfile(user: UserEdit): Flow<Response<Boolean>>
	fun incrementCountProjects(id: String): Flow<Response<Boolean>>

	// Project
	fun createProject(projectData: ProjectCreate): Flow<Response<String>>
	fun uploadUrlImagesProject(images: MutableList<String>, id: String): Flow<Response<Boolean>>
	fun getProjectById(id: String): Flow<Response<ProjectOpen?>>
	fun incrementView(id: String): Flow<Response<Boolean>>
}