package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow


interface UserRepository {

	fun getUserById(id: String): Flow<Response<UserProfile?>>
}