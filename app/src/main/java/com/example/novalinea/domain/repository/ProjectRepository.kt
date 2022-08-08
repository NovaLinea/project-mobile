package com.example.novalinea.domain.repository

import androidx.paging.PagingData
import com.example.novalinea.domain.model.ProjectCreate
import com.example.novalinea.domain.model.ProjectOpen
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

	//fun getProjects(): Flow<Response<List<ProjectTape>>>
	fun getProjects(): Flow<PagingData<ProjectTape>>
	fun getProjectsUser(id: String): Flow<Response<List<ProjectTape>>>
	fun getProjectById(id: String): Flow<Response<ProjectOpen?>>
	fun createProject(project: ProjectCreate, images: MutableList<ByteArray>): Flow<Response<Boolean>>
	fun deleteProject(project: ProjectCreate)
	fun incrementView(id: String): Flow<Response<Boolean>>
}