package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.novalinea.presentation.components.icon_button.IconButtonAction

@Composable
fun ProfileTopBar(
	navController: NavController
) {
	TopAppBar(
		title = {},
		navigationIcon = {
			IconButtonAction(Icons.Default.ArrowBack) {
				navController.popBackStack()
			}
	   	},
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}