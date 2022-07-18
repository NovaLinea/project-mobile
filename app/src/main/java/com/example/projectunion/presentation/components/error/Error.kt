package com.example.projectunion.presentation.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Error(
	message: String,
	background: Color = Color.White
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(background),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = message,
			style = MaterialTheme.typography.body2,
			color = Color.DarkGray
		)
	}
}