package com.example.projectunion.presentation.screens.create.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun CreateTopBar(
	navController: NavController
) {
	TopAppBar(
		title = {
			Box(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = CREATE_SCREEN,
					style = MaterialTheme.typography.h6
				)
			}
		},
		navigationIcon = {
			IconButtonAction(Icons.Default.ArrowBack) {
				navController.popBackStack()
			}
	   	},
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}