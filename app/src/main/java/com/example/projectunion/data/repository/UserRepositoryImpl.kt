package com.example.projectunion.data.repository

import com.example.projectunion.domain.repository.UserRepository

class UserRepositoryImpl() : UserRepository {
	override fun checkAuth(id: String): Boolean {
		return true
	}

}