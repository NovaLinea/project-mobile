package com.example.projectunion.presentation.screens.about_app

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.projectunion.presentation.screens.about_app.components.AboutTopBar

@Composable
fun AboutAppScreen(
	navController: NavController
) {
	Scaffold(
		topBar = {
			AboutTopBar() {
				navController.popBackStack()
			}
	 },
	) {

	}
}