package com.example.projectunion.domain.use_case

data class UseCases(
	// Auth
	val loginByEmail: LoginByEmail,
	val registerByEmail: RegisterByEmail,

	// User
	val checkAuth: CheckAuth,

	// Project
	val getProjects: GetProjects,
	val getProjectById: GetProjectById,
	val createProject: CreateProject,
	val deleteProject: DeleteProject,
)
