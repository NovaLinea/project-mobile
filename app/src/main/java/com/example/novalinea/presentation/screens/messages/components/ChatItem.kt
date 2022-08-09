package com.example.novalinea.presentation.screens.messages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.asTimeLastMessage
import com.example.novalinea.presentation.components.image_painter.ImagePainter
import com.example.novalinea.R

@Composable
fun ChatItem(
	userName: String,
	userPhoto: String?,
	userVerify: Boolean,
	lastMessage: String?,
	timestamp: Any,
	viewed: Boolean,
	countNewMessages: Int,
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
					Row(
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							modifier = Modifier.fillMaxWidth(0.65f),
							text = userName,
							style = MaterialTheme.typography.subtitle1,
							maxLines = 1,
							overflow = TextOverflow.Ellipsis
						)

						if (userVerify) {
							Icon(
								modifier = Modifier
									.size(24.dp)
									.padding(start = 5.dp),
								painter = painterResource(id = R.drawable.ic_verify_account),
								contentDescription = null,
								tint = colorResource(id = R.color.app_blue_verify)
							)
						}
					}

					Row(
						verticalAlignment = Alignment.CenterVertically
					) {
						Box(
							modifier = Modifier
								.padding(end = 2.dp)
								.size(22.dp)
						) {
							var iconViewed = R.drawable.ic_check
							if (viewed)
								iconViewed = R.drawable.ic_double_check

							Icon(
								painter = painterResource(id = iconViewed),
								contentDescription = null,
								tint = colorResource(id = R.color.app_green)
							)
						}
						
						Text(
							text = timestamp.toString().asTimeLastMessage(),
							style = MaterialTheme.typography.caption
						)
					}
				}

				if (lastMessage != null) {
					Row(
						modifier = Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Text(
							text = lastMessage,
							modifier = Modifier.fillMaxWidth(0.9f),
							maxLines = 1,
							overflow = TextOverflow.Ellipsis,
							style = TextStyle(
								color = Color.DarkGray,
								fontWeight = FontWeight.W400,
								fontSize = 15.sp
							)
						)

						if (countNewMessages != 0) {
							Card(
								modifier = Modifier.size(22.dp),
								backgroundColor = colorResource(id = R.color.app_light_blue),
								shape = CircleShape,
								elevation = 0.dp
							) {
								Box(
									modifier = Modifier.fillMaxSize(),
									contentAlignment = Alignment.Center
								) {
									Text(
										text = countNewMessages.toString(),
										overflow = TextOverflow.Ellipsis,
										style = MaterialTheme.typography.caption,
										color = Color.White
									)
								}
							}
						}
					}
				}
			}
		}
	}
}