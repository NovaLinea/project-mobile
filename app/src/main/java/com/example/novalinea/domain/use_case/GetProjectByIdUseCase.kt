package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.ProjectRepository

class GetProjectByIdUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(id: String) = repository.getProjectById(id)
}