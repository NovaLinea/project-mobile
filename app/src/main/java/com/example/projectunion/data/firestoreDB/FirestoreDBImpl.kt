package com.example.projectunion.data.firestoreDB

import com.example.projectunion.common.Constants
import com.example.projectunion.common.Constants.USERS_COLLECTION
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
			val currentDate = Constants.TIME_FORMAT.format(Date())
			val user = hashMapOf(
				"name" to userData.name,
				"email" to userData.email,
				"description" to "",
				"createdAt" to currentDate
			)
			db.collection(USERS_COLLECTION).document(uid).set(user).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}