package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.repository.ProjectRepository

class GetProjects(
	private val repository: ProjectRepository
) {
	operator fun invoke() = repository.getProjects()
}