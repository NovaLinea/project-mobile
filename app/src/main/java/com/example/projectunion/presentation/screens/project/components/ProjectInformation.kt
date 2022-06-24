package com.example.projectunion.presentation.screens.project.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.domain.model.ProjectOpen
import com.example.projectunion.presentation.screens.home.components.ImagePainter


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
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Row(
					modifier = Modifier
						.clickable { onClickCreator() },
					verticalAlignment = Alignment.CenterVertically
				) {
					project.creatorPhoto?.let { photo ->
						Box(
							modifier = Modifier
								.height(25.dp)
								.width(25.dp),
							contentAlignment = Alignment.Center,
						) {
							ImagePainter(
								imageUrl = photo,
								shape = 10f
							) { onClickCreator() }
						}
					}

					Text(
						text = project.creatorName.toString(),
						modifier = Modifier.padding(start = 10.dp),
						style = TextStyle(
							color = Color.DarkGray,
							fontSize = 16.sp,
							fontWeight = FontWeight.W500
						)
					)
				}

				Text(
					text = "time",
					style = TextStyle(
						color = Color.DarkGray,
						fontSize = 16.sp,
						fontWeight = FontWeight.W500
					)
				)
			}

			Column(
				modifier = Modifier.padding(top = 10.dp)
			) {
				project.title?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.h6,
						letterSpacing= 0.2.sp,
						lineHeight = 25.sp
					)
				}
				Spacer(modifier = Modifier.height(5.dp))
				project.description?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.body1,
						letterSpacing= 0.2.sp,
						lineHeight = 25.sp
					)
				}
			}
		}
	}
}