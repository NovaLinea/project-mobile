package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun ChatTopBar(
	onClickBack: () -> Unit
) {
	TopAppBar(
		title = {
			Text(
				text = "Чат",
				style = MaterialTheme.typography.h6
			)
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