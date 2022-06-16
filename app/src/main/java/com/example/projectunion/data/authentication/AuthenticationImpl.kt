package com.example.projectunion.data.authentication

import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import kotlinx.coroutines.flow.Flow

class AuthenticationImpl : Authentication {
	override fun authorized(): Boolean {
		TODO("Not yet implemented")
	}

	override fun loginByEmail(userData: UserLogin): Flow<Response<Boolean>> {
		TODO("Not yet implemented")
	}

	override fun registerByEmail(userData: UserRegister): Flow<Response<Boolean>> {
		TODO("Not yet implemented")
	}

	override fun logout(): Flow<Response<Boolean>> {
		TODO("Not yet implemented")
	}

}