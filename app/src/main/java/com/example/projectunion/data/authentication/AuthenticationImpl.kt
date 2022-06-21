package com.example.projectunion.data.authentication

import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*

class AuthenticationImpl(
	private val auth: FirebaseAuth
) : Authentication {

	override fun authorized() = auth.currentUser != null

	override fun loginByEmail(userData: UserLogin) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			auth.signInWithEmailAndPassword(userData.email, userData.password).await()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun registerByEmail(userData: UserRegister) = flow<Response<FirebaseUser?>> {
		try {
			emit(Response.Loading)
			auth.createUserWithEmailAndPassword(userData.email, userData.password).await()
			emit(Response.Success(auth.currentUser))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun logout() = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			auth.signOut()
			emit(Response.Success(true))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun getAuthCurrentUser() = auth.currentUser

}