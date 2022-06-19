package com.example.projectunion.data.repository

import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.model.Response
import com.example.projectunion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl() : ProjectRepository {
	override fun getProjects() {
		TODO("Not yet implemented")
	}

	override suspend fun getProjectById(id: String): Project? {
		TODO("Not yet implemented")
	}

	override fun createProject(project: Project) = flow<Response<Boolean>> {
		TODO("Not yet implemented")
	}

	override suspend fun deleteProject(project: Project) {
		TODO("Not yet implemented")
	}
}