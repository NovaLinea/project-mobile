package com.example.projectunion.data.firestoreDB

import com.example.projectunion.domain.model.*
import kotlinx.coroutines.flow.Flow

interface FirestoreDB{

	fun createUser(userData: UserRegister, uid: String): Flow<Response<Boolean>>

	fun createProject(projectData: ProjectCreate): Flow<Response<String>>
	fun uploadUrlImagesProject(images: MutableList<String>, id: String): Flow<Response<Boolean>>

	fun getProjects(): Flow<Response<List<ProjectTape>>>
}