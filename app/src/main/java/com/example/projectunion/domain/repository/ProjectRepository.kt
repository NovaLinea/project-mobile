package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

	fun getProjects(): Flow<List<Project>>

	suspend fun getProjectById(id: String): Project?

	suspend fun createProject(project: Project)

	suspend fun deleteProject(project: Project)
}