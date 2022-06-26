package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.ProjectRepository

class GetProjectsUserUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(id: String) = repository.getProjectsUser(id)
}