package com.example.projectunion.presentation.screens.about_app

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.projectunion.common.Constants.ABOUT_APP_SCREEN
import com.example.projectunion.presentation.components.top_bar.TopBar

@Composable
fun AboutAppScreen() {
	Scaffold(
		topBar = { TopBar(title = ABOUT_APP_SCREEN) },
	) {

	}
}