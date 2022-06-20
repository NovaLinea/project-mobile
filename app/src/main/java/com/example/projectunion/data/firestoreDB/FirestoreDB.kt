package com.example.projectunion.data.firestoreDB

import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserRegister
import kotlinx.coroutines.flow.Flow

interface FirestoreDB{

	fun createUser(userData: UserRegister, uid: String): Flow<Response<Boolean>>
	fun createProject(projectData: Project): Flow<Response<String>>
}