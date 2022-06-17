package com.example.projectunion.domain.use_case

import com.example.projectunion.domain.model.Project
import com.example.projectunion.domain.repository.ProjectRepository

class CreateProjectUseCase(
    private val repository: ProjectRepository
) {

    suspend operator fun invoke(projectData: Project) = repository.createProject(projectData)
}