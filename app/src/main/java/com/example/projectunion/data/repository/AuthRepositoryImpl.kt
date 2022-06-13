package com.example.projectunion.data.repository

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister
import com.example.projectunion.domain.repository.AuthRepository

class AuthRepositoryImpl() : AuthRepository {
	override fun loginByEmail(userData: UserLogin): Boolean {
		return true
	}

	override fun registerByEmail(userData: UserRegister): Boolean {
		return true
	}

}