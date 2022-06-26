package com.example.projectunion.data.repository

import android.net.Uri
import com.example.projectunion.data.firestoreDB.FirestoreDB
import com.example.projectunion.data.storage.Storage
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : ProjectRepository {

	override fun getProjects() = firestoreDB.getProjects()

	override fun getProjectsUser(id: String) = firestoreDB.getProjectsUser(id)

	override fun getProjectById(id: String) = firestoreDB.getProjectById(id)

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
							storageDB.addImagesProject(images, response.data).collect { respImg ->
								when(respImg) {
									is Response.Success ->
										firestoreDB.uploadUrlImagesProject(respImg.data, response.data).collect {
											emit(it)
										}
									is Response.Error -> emit(respImg)
									is Response.Loading -> emit(respImg)
								}
							}
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