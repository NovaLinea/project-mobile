package com.example.novalinea.data.firestoreDB

import com.example.novalinea.common.Constants.COUNT_PROJECTS_FIELD
import com.example.novalinea.common.Constants.CREATED_AT_FIELD
import com.example.novalinea.common.Constants.CREATOR_ID_PROJECT_FIELD
import com.example.novalinea.common.Constants.DESCRIPTION_FIELD
import com.example.novalinea.common.Constants.DESCRIPTION_USER_FIELD
import com.example.novalinea.common.Constants.EMAIL_USER_FIELD
import com.example.novalinea.common.Constants.IMAGES_PROJECT_FIELD
import com.example.novalinea.common.Constants.LIKES_PROJECT_FIELD
import com.example.novalinea.common.Constants.NAME_USER_FIELD
import com.example.novalinea.common.Constants.PHOTO_USER_FIELD
import com.example.novalinea.common.Constants.PRICE_PROJECT_FIELD
import com.example.novalinea.common.Constants.PROJECTS_COLLECTION
import com.example.novalinea.common.Constants.STAFF_PROJECT_FIELD
import com.example.novalinea.common.Constants.TITLE_PROJECT_FIELD
import com.example.novalinea.common.Constants.TYPE_PROJECT_FIELD
import com.example.novalinea.common.Constants.UPDATED_AT_FIELD
import com.example.novalinea.common.Constants.USERNAME_USER_FIELD
import com.example.novalinea.common.Constants.USERS_COLLECTION
import com.example.novalinea.common.Constants.VERIFY_USER_FIELD
import com.example.novalinea.common.Constants.VIEWS_PROJECT_FIELD
import com.example.novalinea.domain.model.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirestoreDBImpl(
	private val db: FirebaseFirestore
): FirestoreDB {

	// User
	override fun checkEmail(email: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val user = db.collection(USERS_COLLECTION)
				.whereEqualTo(EMAIL_USER_FIELD, email)
				.get().await()

			if (user.isEmpty)
				emit(Response.Success(true))
			else
				emit(Response.Success(false))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun checkUserName(username: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val user = db.collection(USERS_COLLECTION)
				.whereEqualTo(USERNAME_USER_FIELD, username)
				.get().await()

			if (user.isEmpty)
				emit(Response.Success(true))
			else
				emit(Response.Success(false))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun createUser(
		userData: UserRegister,
		uid: String
	) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)

			val mapUser = hashMapOf<String, Any>()
			mapUser[NAME_USER_FIELD] = userData.name
			mapUser[USERNAME_USER_FIELD] = userData.username
			mapUser[EMAIL_USER_FIELD] = userData.email
			mapUser[VERIFY_USER_FIELD] = false
			mapUser[COUNT_PROJECTS_FIELD] = 0
 			mapUser[CREATED_AT_FIELD] = FieldValue.serverTimestamp()

			db.collection(USERS_COLLECTION).document(uid).set(mapUser).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getUserById(id: String) = flow<Response<UserProfile?>> {
		try {
			emit(Response.Loading)
			val user = db.collection(USERS_COLLECTION)
				.document(id).get().await()
				.toObject(UserProfile::class.java)
			user?.let {
				it.id = id
			}
			emit(Response.Success(user))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun uploadPhotoUser(photo: String, id: String) = flow<Response<String>> {
		try {
			emit(Response.Loading)
			db.collection(USERS_COLLECTION).document(id).update(PHOTO_USER_FIELD, photo).await()
			emit(Response.Success(photo))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun deletePhotoUser(id: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			db.collection(USERS_COLLECTION)
				.document(id)
				.update(PHOTO_USER_FIELD, FieldValue.delete())
				.await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun editProfile(user: UserEdit) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			db.collection(USERS_COLLECTION).document(user.id).update(
				NAME_USER_FIELD, user.name,
				DESCRIPTION_USER_FIELD, user.description
			).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun incrementCountProjects(id: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			db.collection(USERS_COLLECTION)
				.document(id)
				.update(COUNT_PROJECTS_FIELD, FieldValue.increment(1))
				.await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}


	// Project
	override fun createProject(projectData: ProjectCreate) = flow<Response<String>> {
		try {
			emit(Response.Loading)

			val mapProject = hashMapOf<String, Any>()
			mapProject[TITLE_PROJECT_FIELD] = projectData.title
			mapProject[DESCRIPTION_FIELD] = projectData.description
			mapProject[TYPE_PROJECT_FIELD] = projectData.type
			mapProject[VIEWS_PROJECT_FIELD] = 0
			mapProject[LIKES_PROJECT_FIELD] = 0
			mapProject[CREATOR_ID_PROJECT_FIELD] = projectData.creatorID
			mapProject[CREATED_AT_FIELD] = FieldValue.serverTimestamp()
			mapProject[UPDATED_AT_FIELD] = FieldValue.serverTimestamp()

			when(projectData.type) {
				TypesProject.SALE ->
					mapProject[PRICE_PROJECT_FIELD] = projectData.price
				TypesProject.TEAM ->
					mapProject[STAFF_PROJECT_FIELD] = projectData.staff
			}

			val projectID = db.collection(PROJECTS_COLLECTION).add(mapProject).await().id
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

	override fun getProjectById(id: String) = flow<Response<ProjectOpen?>> {
		try {
			emit(Response.Loading)
			val project = db.collection(PROJECTS_COLLECTION)
				.document(id).get().await()
				.toObject(ProjectOpen::class.java)
			emit(Response.Success(project))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun incrementView(id: String) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			db.collection(PROJECTS_COLLECTION)
				.document(id)
				.update(VIEWS_PROJECT_FIELD, FieldValue.increment(1))
				.await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}
