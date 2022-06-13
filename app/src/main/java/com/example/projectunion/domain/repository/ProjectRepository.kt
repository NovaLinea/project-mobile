package com.example.projectunion.domain.repository

import com.example.projectunion.domain.model.Project

interface ProjectRepository {

	fun getProjects()

	suspend fun getProjectById(id: String): Project?

	suspend fun createProject(project: Project)

	suspend fun deleteProject(project: Project)
}