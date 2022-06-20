package com.example.projectunion.data.repository

import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : ProjectRepository {

	override fun getProjects() {
		TODO("Not yet implemented")
	}

	override suspend fun getProjectById(id: String): Project? {
		TODO("Not yet implemented")
	}

	override fun createProject(project: Project) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			firestoreDB.createProject(project).collect { response ->
				when(response) {
					is Response.Loading -> emit(Response.Loading)
					is Response.Success -> {
						if (project.photo != null)
							storageDB.addPhotoProject(project.photo, "uid").collect { emit(it) }
						else
							emit(response)
					}
					is Response.Error -> emit(response)
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override suspend fun deleteProject(project: Project) {
		TODO("Not yet implemented")
	}
}