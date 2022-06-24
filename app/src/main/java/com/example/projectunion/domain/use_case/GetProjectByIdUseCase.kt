package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.ProjectRepository

class GetProjectByIdUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(id: String) = repository.getProjectById(id)
}