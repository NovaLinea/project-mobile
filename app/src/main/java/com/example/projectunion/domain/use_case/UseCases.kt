package com.example.projectunion.domain.use_case

data class UseCases(
	// Auth
	val loginByEmail: LoginByEmailUseCase,
	val registerByEmail: RegisterByEmailUseCase,

	// User
	val checkAuth: CheckAuthorizedUseCase,

	// Project
	val getProjects: GetProjectsUseCase,
	val getProjectById: GetProjectByIdUseCase,
	val createProject: CreateProjectUseCase,
	val deleteProject: DeleteProjectUseCase,
)
