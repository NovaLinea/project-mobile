package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.icon_button.IconButtonAction
import com.example.projectunion.presentation.components.image_painter.ImagePainter

@Composable
fun ChatTopBar(
	title: String,
	onClickBack: () -> Unit,
	onClickUser: () -> Unit
) {
	TopAppBar(
		title = {
			Row(
				modifier = Modifier.clickable {
					onClickUser()
				},
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Start
			) {
				/*Box(
					modifier = Modifier
						.height(35.dp)
						.width(35.dp),
					contentAlignment = Alignment.Center,
				) {
					ImagePainter(
						imageUrl = photo,
						isCircle = true,
						onClick = { onClickUser() }
					)
				}*/
				Text(
					text = title,
					style = MaterialTheme.typography.h6
				)
			}
		},
		navigationIcon = {
			IconButtonAction(Icons.Default.ArrowBack) {
				onClickBack()
			}
		},
		actions = {
			IconButtonAction(Icons.Default.MoreVert) {

			}
		},
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}