package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

	fun getProjects()
	suspend fun getProjectById(id: String): Project?
	fun createProject(project: Project): Flow<Response<Boolean>>
	suspend fun deleteProject(project: Project)
}