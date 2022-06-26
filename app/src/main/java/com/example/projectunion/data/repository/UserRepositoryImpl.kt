package com.example.projectunion.data.repository

import android.net.Uri
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : UserRepository {

	override fun getUserById(id: String) = firestoreDB.getUserById(id)

	override fun uploadPhotoUser(photo: Uri, id: String) = flow<Response<Boolean>> {
		try {
			storageDB.addPhotoUser(photo, id).collect { response ->
				when(response) {
					is Response.Success ->
						firestoreDB.uploadPhotoUser(response.data, id).collect {
							emit(it)
						}
					is Response.Error -> emit(response)
					is Response.Loading -> emit(response)
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}
}