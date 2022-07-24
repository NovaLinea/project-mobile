package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.ProjectRepository

class GetProjectsUserUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(id: String) = repository.getProjectsUser(id)
}