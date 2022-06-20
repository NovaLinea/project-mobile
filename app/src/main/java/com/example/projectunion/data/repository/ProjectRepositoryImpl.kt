package com.example.projectunion.data.repository

import android.net.Uri
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : ProjectRepository {

	override fun getProjects() = firestoreDB.getProjects()

	override fun getProjectById(id: String): ProjectCreate? {
		TODO("Not yet implemented")
	}

	override fun createProject(
		project: ProjectCreate,
		images: MutableList<Uri>
	) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			firestoreDB.createProject(project).collect { response ->
				when(response) {
					is Response.Loading -> emit(Response.Loading)
					is Response.Success -> {
						if (images.isNotEmpty())
							storageDB.addPhotoProject(images, response.data).collect { emit(it) }
						else
							emit(Response.Success(true))
					}
					is Response.Error -> emit(response)
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun deleteProject(project: ProjectCreate) {
		TODO("Not yet implemented")
	}
}