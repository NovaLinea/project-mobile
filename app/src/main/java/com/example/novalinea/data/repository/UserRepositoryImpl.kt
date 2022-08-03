package com.example.novalinea.data.repository

import android.content.Context
import android.net.Uri
import com.example.novalinea.data.firestoreDB.FirestoreDB
import com.example.novalinea.data.storage.Storage
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.model.UserEdit
import com.example.novalinea.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : UserRepository {

	override fun getUserById(id: String) = firestoreDB.getUserById(id)

	override fun uploadPhotoUser(photo: Uri, id: String) = flow<Response<String>> {
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

	override fun deletePhotoUser(id: String) = flow<Response<Boolean>> {
		try {
			storageDB.deletePhotoUser(id).collect { response ->
				when(response) {
					is Response.Success ->
						firestoreDB.deletePhotoUser(id).collect {
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

	override fun editProfile(user: UserEdit) = firestoreDB.editProfile(user)
}