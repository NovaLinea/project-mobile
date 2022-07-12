package com.example.projectunion.presentation.screens.create.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun CreateTopBar(
	navController: NavController
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
				navController.popBackStack()
			}
		}

		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = CREATE_SCREEN,
				style = MaterialTheme.typography.h6
			)
		}
	}
}