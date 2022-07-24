package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.*
import com.example.novalinea.domain.model.ProjectOpen
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.components.creator_project.CreatorProject
import com.example.novalinea.presentation.ui.theme.OpenSans
import com.google.firebase.Timestamp

@Composable
fun ProjectInformation(
	project: ProjectTape,
	onClickCreator: () -> Unit
) {
	//val createdAt = project.createdAt as Timestamp

	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		Column(
			modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				CreatorProject(
					creatorName = project.creatorName,
					creatorPhoto = project.creatorPhoto,
					onClickCreator = { onClickCreator() }
				)

				/*Text(
					text = createdAt.asCreatedAt(),
					style = TextStyle(
						color = Color.DarkGray,
						fontSize = 15.sp,
						fontWeight = FontWeight.W500,
						fontFamily = OpenSans
					)
				)*/
			}

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