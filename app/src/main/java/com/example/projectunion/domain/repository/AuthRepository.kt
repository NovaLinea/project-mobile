package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface AuthRepository {

	fun authorized(id: String): Boolean
	fun loginByEmail(userData: UserLogin): Boolean
	fun registerByEmail(userData: UserRegister): Boolean
}