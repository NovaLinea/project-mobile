package com.example.projectunion.data.firestoreDB

import com.example.projectunion.domain.model.*
import kotlinx.coroutines.flow.Flow

interface FirestoreDB{

	fun createUser(userData: UserRegister, uid: String): Flow<Response<Boolean>>
	fun getUserById(id: String): Flow<Response<UserProfile?>>

	fun createProject(projectData: ProjectCreate): Flow<Response<String>>
	fun uploadUrlImagesProject(images: MutableList<String>, id: String): Flow<Response<Boolean>>
	fun getProjectById(id: String): Flow<Response<ProjectOpen?>>
	fun getProjects(): Flow<Response<List<ProjectTape>>>
	fun getProjectsUser(id: String): Flow<Response<List<ProjectTape>>>
}