package com.example.projectunion.data.repository

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
	private val auth: FirebaseAuth
) : AuthRepository {

	override fun authorized(id: String): Boolean {
		return true
	}

	override fun loginByEmail(userData: UserLogin): Boolean {
		try {
			auth.signInWithEmailAndPassword(userData.email, userData.password)
			return true
		} catch (e: Exception) {
			return false
		}
	}

	override fun registerByEmail(userData: UserRegister): Boolean {
		return true
	}

}