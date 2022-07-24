package com.example.novalinea.domain.use_case

import com.example.novalinea.domain.repository.ProjectRepository

class IncrementViewUseCase(
	private val repository: ProjectRepository
) {

	operator fun invoke(id: String) = repository.incrementView(id)
}