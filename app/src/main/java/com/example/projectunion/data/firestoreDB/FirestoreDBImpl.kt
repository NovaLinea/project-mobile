package com.example.projectunion.data.firestoreDB

import com.example.projectunion.common.Constants.IMAGES_PROJECT_FIELD
import com.example.projectunion.common.Constants.PROJECTS_COLLECTION
import com.example.projectunion.common.Constants.USERS_COLLECTION
import com.example.projectunion.domain.model.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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
				"photo" to null,
				//"createdAt" to TIME_FORMAT.format(Date())
				"createdAt" to Date()
			)
			db.collection(USERS_COLLECTION).document(uid).set(user).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun createProject(projectData: ProjectCreate) = flow<Response<String>> {
		try {
			emit(Response.Loading)
			val projectID = db.collection(PROJECTS_COLLECTION).add(projectData).await().id
			emit(Response.Success(projectID))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun uploadUrlImagesProject(images: MutableList<String>, id: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			db.collection(PROJECTS_COLLECTION).document(id).update(IMAGES_PROJECT_FIELD, images).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getProjects() = flow<Response<List<ProjectTape>>> {
		try {
			emit(Response.Loading)
			val projects = db.collection(PROJECTS_COLLECTION)
				.orderBy("createdAt", Query.Direction.DESCENDING)
				.get().await().map { document ->
					var project = document.toObject(ProjectTape::class.java)
					project.id = document.id
					project
			}
			emit(Response.Success(projects))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}
