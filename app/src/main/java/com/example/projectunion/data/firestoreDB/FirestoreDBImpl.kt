package com.example.projectunion.data.firestoreDB

import com.example.projectunion.common.Constants.TIME_FORMAT
import com.example.projectunion.common.Constants.PROJECTS_COLLECTION
import com.example.projectunion.common.Constants.USERS_COLLECTION
import com.example.projectunion.data.firestoreDB.model.ProjectCreate
import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserRegister
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*

class FirestoreDBImpl(
	private val db: FirebaseFirestore
): FirestoreDB {

	override fun createUser(
		userData: UserRegister,
		uid: String
	) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			val user = hashMapOf(
				"name" to userData.name,
				"email" to userData.email,
				"description" to "",
				"createdAt" to TIME_FORMAT.format(Date())
			)
			db.collection(USERS_COLLECTION).document(uid).set(user).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun createProject(projectData: Project) = flow<Response<String>> {
		try {
			emit(Response.Loading)
			val project = ProjectCreate(
				title = projectData.title,
				description = projectData.description,
				type = projectData.type,
				price = projectData.price,
				createdAt = projectData.createdAt,
				updatedAt = projectData.updatedAt,
				likes = projectData.likes,
				views = projectData.views,
				creatorID = projectData.creatorID
			)
			val projectID = db.collection(PROJECTS_COLLECTION).add(project).await().id
			emit(Response.Success(projectID))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}