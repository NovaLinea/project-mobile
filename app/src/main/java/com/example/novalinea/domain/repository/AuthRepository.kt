package com.example.novalinea.domain.repository

import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserLogin
import com.example.novalinea.domain.model.UserRegister
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

	fun authorized(): Boolean
	fun verified(): Boolean?
	fun checkEmail(email: String): Flow<Response<Boolean>>
	fun checkUserName(username: String): Flow<Response<Boolean>>
	fun loginByEmail(userData: UserLogin): Flow<Response<Boolean>>
	fun registerByEmail(userData: UserRegister): Flow<Response<Boolean>>
	fun logout(): Flow<Response<Boolean>>
	fun getAuthCurrentUser(): FirebaseUser?
}