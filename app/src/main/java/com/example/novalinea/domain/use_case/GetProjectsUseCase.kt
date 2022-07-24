package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.ProjectRepository

class GetProjectsUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke() = repository.getProjects()
}