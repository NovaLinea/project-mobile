package com.example.projectunion.data.repository

import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.repository.UserRepository

class UserRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : UserRepository {

	override fun getUserById(id: String) = firestoreDB.getUserById(id)
}