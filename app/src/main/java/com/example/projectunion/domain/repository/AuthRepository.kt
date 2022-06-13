package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.UserLogin
import com.example.projectunion.domain.model.UserRegister

interface AuthRepository {
	fun loginByEmail(userData: UserLogin): Boolean
	fun registerByEmail(userData: UserRegister): Boolean
}