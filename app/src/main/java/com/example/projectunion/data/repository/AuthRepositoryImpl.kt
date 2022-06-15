package com.example.projectunion.data.repository

import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.Response.*
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val auth: FirebaseAuth
) : AuthRepository {

	override fun authorized() = auth.currentUser != null

	override suspend fun loginByEmail(userData: UserLogin) = flow<Response<Boolean>> {
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
			emit(Success(true))
		} catch (e: Exception) {
			emit(Error(e.message ?: e.toString()))
		}
	}

	override fun logout() {
		auth.signOut()
	}

}