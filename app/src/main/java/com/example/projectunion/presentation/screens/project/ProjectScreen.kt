package com.example.projectunion.presentation.screens.project

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.projectunion.presentation.screens.project.components.project_top_bar.ProjectTopBar

@Composable
fun ProjectScreen(
	projectID: String,
	navController: NavHostController
) {
	Scaffold(
		topBar = { ProjectTopBar(navController) }
	) {
		Text(text = "Project #$projectID")
	}
}