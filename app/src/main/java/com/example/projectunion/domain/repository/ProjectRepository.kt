package com.example.projectunion.domain.repository

import android.net.Uri
import com.example.projectunion.domain.model.ProjectCreate
import com.example.projectunion.domain.model.ProjectTape
import com.example.projectunion.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

	fun getProjects(): Flow<Response<List<ProjectTape>>>
	fun getProjectById(id: String): ProjectCreate?
	fun createProject(project: ProjectCreate, images: MutableList<Uri>): Flow<Response<Boolean>>
	fun deleteProject(project: ProjectCreate)
}