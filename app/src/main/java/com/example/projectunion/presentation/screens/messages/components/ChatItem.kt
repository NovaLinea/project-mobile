package com.example.projectunion.presentation.screens.messages.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import com.example.projectunion.R
import com.example.projectunion.common.asTime
import com.example.projectunion.presentation.components.image_painter.ImagePainter
import com.example.projectunion.presentation.ui.theme.OpenSans

@Composable
fun ChatItem(
	userName: String,
	userPhoto: String?,
	lastMessage: String?,
	timestamp: Any,
	onOpenChat: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clickable {
				onOpenChat()
			}
	) {
		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth()
		) {
			Card(
				modifier = Modifier.size(55.dp),
				shape = CircleShape,
				elevation = 0.dp,
			) {
				ImagePainter(
					imageUrl = userPhoto,
					isCircle = true,
					errorPhoto = R.drawable.ic_person_fill,
					onClick = { onOpenChat() }
				)
			}

			Column(
				modifier = Modifier
					.padding(start = 12.dp, top = 3.dp, end = 10.dp)
					.fillMaxWidth(),
				verticalArrangement = Arrangement.SpaceBetween
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = userName,
						style = MaterialTheme.typography.subtitle1,
						letterSpacing = 0.sp,
					)

					Text(
						text = timestamp.toString().asTime(),
						style = TextStyle(
							color = Color.DarkGray,
							fontFamily = OpenSans,
							fontWeight = FontWeight.W400,
							fontSize = 13.sp
						)
					)
				}

				if (lastMessage != null) {
					Text(
						text = lastMessage,
						modifier = Modifier.fillMaxWidth(),
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						style = TextStyle(
							color = Color.DarkGray,
							fontWeight = FontWeight.W400,
							fontSize = 15.sp
						)
					)
				}
			}
		}
	}
}