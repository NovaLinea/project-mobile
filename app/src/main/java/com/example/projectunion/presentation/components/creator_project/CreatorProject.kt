package com.example.projectunion.presentation.components.creator_project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.ui.theme.*

@Composable
fun CreatorProject(
	creatorName: String?,
	creatorPhoto: String?,
	onClickCreator: () -> Unit
) {
	Row(
		modifier = Modifier.clickable { onClickCreator() },
		verticalAlignment = Alignment.CenterVertically
	) {
		creatorPhoto?.let { photo ->
			Box(
				modifier = Modifier
					.height(20.dp)
					.width(20.dp),
				contentAlignment = Alignment.Center,
			) {
				ImagePainter(
					imageUrl = photo,
					shape = 10f
				) { onClickCreator() }
			}
		}

		creatorName?.let { name ->
			Text(
				text = name,
				modifier = Modifier.padding(start = 10.dp),
				style = TextStyle(
					color = Color.DarkGray,
					fontFamily = OpenSans,
					fontSize = 15.sp,
					fontWeight = FontWeight.W500
				)
			)
		}
	}
}