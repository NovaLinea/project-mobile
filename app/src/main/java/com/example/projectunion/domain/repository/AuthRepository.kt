package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

	fun authorized(): Boolean
	suspend fun loginByEmail(userData: UserLogin): Flow<Response<Boolean>>
	fun registerByEmail(userData: UserRegister): Boolean
	fun logout()
}