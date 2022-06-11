package com.example.projectunion.domain.repository


interface UserRepository {
	fun checkAuth(id: String): Boolean
}