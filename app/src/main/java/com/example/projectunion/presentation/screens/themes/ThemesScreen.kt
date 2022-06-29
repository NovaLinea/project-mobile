package com.example.projectunion.presentation.screens.themes

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.projectunion.common.Constants.THEMES_SCREEN
import com.example.projectunion.presentation.components.top_bar.TopBar

@Composable
fun ThemesScreen() {
	Scaffold(
		topBar = { TopBar(title = THEMES_SCREEN) },
	) {

	}
}