package com.example.projectunion.domain.use_case

import android.net.Uri
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.repository.ProjectRepository

class CreateProjectUseCase(
    private val repository: ProjectRepository
) {

    operator fun invoke(
        projectData: ProjectCreate,
        images: MutableList<Uri>
    ) = repository.createProject(projectData, images)
}