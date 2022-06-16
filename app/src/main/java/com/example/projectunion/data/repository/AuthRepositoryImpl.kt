package com.example.projectunion.data.repository

import com.example.projectunion.common.Constants.TIME_FORMAT
import com.example.projectunion.common.Constants.USERS_COLLECTION
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val auth: FirebaseAuth,
	private val db: FirebaseFirestore
) : AuthRepository {

	override fun authorized() = auth.currentUser != null

	override fun loginByEmail(userData: UserLogin) = flow<Response<Boolean>> {
		try {
			emit(Loading)
			auth.signInWithEmailAndPassword(userData.email, userData.password).await()
			emit(Success(true))
		} catch (e: Exception) {
			emit(Error(e.message ?: e.toString()))
		}
	}

	override fun registerByEmail(userData: UserRegister) = flow<Response<Boolean>> {
		try {
			emit(Loading)
			auth.createUserWithEmailAndPassword(userData.email, userData.password).await()

			auth.currentUser?.let { data ->
				val currentDate = TIME_FORMAT.format(Date())
				val user = hashMapOf(
					"name" to userData.name,
					"email" to userData.email,
					"description" to "",
					"createdAt" to currentDate
				)
				db.collection("users").document(data.uid).set(user).await()
			}

			emit(Success(true))
		} catch (e: Exception) {
			emit(Error(e.message ?: e.toString()))
		}
	}

	override fun logout() = flow<Response<Boolean>> {
		try {
			emit(Loading)
			auth.signOut()
			emit(Success(true))
		} catch (e: Exception) {
			emit(Error(e.message ?: e.toString()))
		}
	}

}