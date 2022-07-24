package com.example.novalinea.data.authentication

import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserLogin
import com.example.novalinea.domain.model.UserRegister
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface Authentication {

	fun authorized(): Boolean
	fun verified(): Boolean?
	fun loginByEmail(userData: UserLogin): Flow<Response<Boolean>>
	fun registerByEmail(userData: UserRegister): Flow<Response<FirebaseUser?>>
	fun verifyEmail(): Flow<Response<Boolean>>
	fun logout(): Flow<Response<Boolean>>
	fun getAuthCurrentUser(): FirebaseUser?
}