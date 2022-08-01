package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.asCreatedAt
import com.example.novalinea.domain.model.ProjectOpen
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.domain.model.Response
import com.example.novalinea.presentation.components.creator_project.CreatorProject
import com.example.novalinea.presentation.components.staff_project.StaffProject
import com.example.novalinea.presentation.components.type_project.TypeProject
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun ProjectInformation(
	project: ProjectTape,
	additionallyData: Response<ProjectOpen?>,
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

				if (additionallyData is Response.Success) {
					additionallyData.data?.createdAt?.let { createdAt ->
						Text(
							text = createdAt.asCreatedAt(),
							style = TextStyle(
								color = Color.DarkGray,
								fontSize = 15.sp,
								fontWeight = FontWeight.W500,
								fontFamily = OpenSans
							)
						)
					}
				}
			}
			Spacer(modifier = Modifier.height(11.dp))

			project.type?.let { type ->
				TypeProject(type = type)
			}
			Spacer(modifier = Modifier.height(7.dp))

			project.title?.let { title ->
				Text(
					text = title,
					style = MaterialTheme.typography.h5,
					overflow = TextOverflow.Ellipsis,
					letterSpacing = 0.2.sp,
					lineHeight = 25.sp
				)
			}

			project.images?.let { images ->
				SliderImagesProject(images = images)
			}

			if (project.description != null && project.description.isNotEmpty()) {
				if (project.images == null)
					Spacer(modifier = Modifier.height(7.dp))
				else
					Spacer(modifier = Modifier.height(15.dp))

				Text(
					text = project.description,
					style = MaterialTheme.typography.body2,
					overflow = TextOverflow.Ellipsis,
					letterSpacing = 0.2.sp,
					lineHeight = 25.sp
				)
			}

			project.staff?.let {staff ->
				Spacer(modifier = Modifier.height(15.dp))
				StaffProject(staff = staff, isAllVisible = true)
			}
		}

		if (additionallyData is Response.Success) {
			ProjectFeedback(
				views = additionallyData.data?.views
			)
		}
	}
}