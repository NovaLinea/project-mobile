package com.example.projectunion.presentation.screens.project

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projectunion.presentation.screens.project.components.ProjectTopBar

@Composable
fun ProjectScreen(
	projectID: String,
	navController: NavHostController,
	viewModel: ProjectViewModel = hiltViewModel()
) {
	viewModel.userID.value = projectID

	Scaffold(
		topBar = { ProjectTopBar(navController) }
	) {
		Text(text = "Project #$projectID")
	}
}