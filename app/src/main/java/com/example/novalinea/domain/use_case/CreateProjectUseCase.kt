package com.example.novalinea.domain.use_case

import android.net.Uri
import com.example.novalinea.domain.model.ProjectCreate
import com.example.novalinea.domain.repository.ProjectRepository

class CreateProjectUseCase(
    private val repository: ProjectRepository
) {

    operator fun invoke(
        projectData: ProjectCreate,
        images: MutableList<Uri>
    ) = repository.createProject(projectData, images)
}