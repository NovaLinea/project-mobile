package com.example.projectunion.presentation.screens.home

import com.example.projectunion.domain.model.Project

data class HomeState(
	val isLoading: Boolean = false,
	val projects: List<Project> = emptyList(),
	val error: String = ""
)