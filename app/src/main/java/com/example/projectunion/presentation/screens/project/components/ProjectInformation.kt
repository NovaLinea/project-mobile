package com.example.projectunion.presentation.screens.project.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.projectunion.domain.model.ProjectOpen

@Composable
fun ProjectInformation(project: ProjectOpen) {
	project.title?.let { Text(text = it) }

	project.description?.let { Text(text = it) }
}