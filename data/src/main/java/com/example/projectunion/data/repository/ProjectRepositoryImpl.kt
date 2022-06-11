package com.example.projectunion.data.repository

import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow

class ProjectRepositoryImpl() : ProjectRepository {
	override fun getProjects(): Flow<List<Project>> {
		TODO("Not yet implemented")
	}

	override suspend fun getProjectById(id: String): Project? {
		TODO("Not yet implemented")
	}

	override suspend fun createProject(project: Project) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteProject(project: Project) {
		TODO("Not yet implemented")
	}
}