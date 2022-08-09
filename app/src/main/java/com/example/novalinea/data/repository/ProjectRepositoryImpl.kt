package com.example.novalinea.data.repository

import com.example.novalinea.data.firestoreDB.FirestoreDB
import com.example.novalinea.data.storage.Storage
import com.example.novalinea.domain.model.ProjectCreate
import com.example.novalinea.domain.model.Response
import com.example.novalinea.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl(
	private val firestoreDB: FirestoreDB,
	private val storageDB: Storage
) : ProjectRepository {

	//override fun getProjectsUser(id: String) = firestoreDB.getProjectsUser(id)

	override fun getProjectById(id: String) = firestoreDB.getProjectById(id)

	override fun createProject(
		project: ProjectCreate,
		images: MutableList<ByteArray>
	) = flow<Response<Boolean>> {
		try {
			emit(Response.Loading)
			firestoreDB.createProject(project).collect { responseCreate ->
				when(responseCreate) {
					is Response.Loading -> emit(responseCreate)
					is Response.Error -> emit(responseCreate)
					is Response.Success -> {
						if (images.isNotEmpty())
							storageDB.addImagesProject(
								images = images,
								id = responseCreate.data
							).collect { responseImage ->
								when(responseImage) {
									is Response.Error -> emit(responseImage)
									is Response.Loading -> emit(responseImage)
									is Response.Success -> {
										firestoreDB.uploadUrlImagesProject(
											images = responseImage.data,
											id = responseCreate.data
										).collect { responseUpload ->
											emit(responseUpload)
										}
									}
								}
							}
						else
							emit(Response.Success(true))

						firestoreDB.incrementCountProjects(project.creatorID).collect { responseIncrement ->
							emit(responseIncrement)
						}
					}
				}
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: e.toString()))
		}
	}

	override fun deleteProject(project: ProjectCreate) {
		TODO("Not yet implemented")
	}

	override fun incrementView(id: String) = firestoreDB.incrementView(id)
}