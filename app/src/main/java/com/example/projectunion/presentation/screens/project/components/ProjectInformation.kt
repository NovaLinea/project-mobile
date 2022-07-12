package com.example.projectunion.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.presentation.components.header_project.HeaderProject

@Composable
fun ProjectInformation(
	project: ProjectOpen,
	onClickCreator: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		Column(
			modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
		) {
			HeaderProject(
				creatorName = project.creatorName,
				creatorPhoto = project.creatorPhoto,
				onClickCreator = { onClickCreator() },
				time = project.createdAt
			)

			Spacer(modifier = Modifier.height(10.dp))

			BodyProject(
				title = project.title,
				description = project.description
			)
		}

		project.images?.let { images ->
			Spacer(modifier = Modifier.height(10.dp))
			SliderImagesProject(images = images)
		}

		FeedbackProject(
			views = project.views
		)
	}
}