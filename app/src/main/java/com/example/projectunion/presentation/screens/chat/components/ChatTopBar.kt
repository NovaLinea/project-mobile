package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.common.Constants
import com.example.projectunion.presentation.components.icon_button.IconButtonAction
import com.example.projectunion.presentation.components.image_painter.ImagePainter

@Composable
fun ChatTopBar(
	title: String,
	photo: String?,
	onClickBack: () -> Unit,
	onClickUser: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
	) {
		Box(
			modifier = Modifier
				.padding(start = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			IconButtonAction(Icons.Default.ArrowBack) {
				onClickBack()
			}
		}

		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(
				modifier = Modifier.padding(start = 10.dp),
				text = title,
				style = TextStyle(
					color = Color.Black,
					fontWeight = FontWeight.W500,
					fontSize = 18.sp
				)
			)
		}

		Box(
			modifier = Modifier
				.padding(end = 15.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterEnd
		) {
			Card(
				modifier = Modifier
					.height(35.dp)
					.width(35.dp),
				shape = CircleShape,
				elevation = 0.dp,
			) {
				if (photo == null) {
					Box(
						modifier = Modifier
							.fillMaxSize()
							.background(Color.LightGray),
						contentAlignment = Alignment.Center
					) {
						Icon(
							modifier = Modifier.padding(5.dp),
							imageVector = Icons.Default.Person,
							contentDescription = null
						)
					}
				} else {
					ImagePainter(
						imageUrl = photo,
						isCircle = true,
						onClick = { onClickUser() }
					)
				}
			}
		}
	}

	/*TopAppBar(
		title = {
			Row(
				modifier = Modifier.clickable {
					onClickUser()
				},
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Start
			) {
				Card(
					modifier = Modifier
						.height(35.dp)
						.width(35.dp),
					shape = CircleShape,
					elevation = 0.dp,
				) {
					if (photo == null) {
						Box(
							modifier = Modifier
								.fillMaxSize()
								.background(Color.LightGray),
							contentAlignment = Alignment.Center
						) {
							Icon(
								modifier = Modifier.padding(5.dp),
								imageVector = Icons.Default.Person,
								contentDescription = null
							)
						}
					} else {
						ImagePainter(
							imageUrl = photo,
							isCircle = true,
							onClick = { onClickUser() }
						)
					}
				}
		},
	)*/
}