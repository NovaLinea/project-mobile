package com.example.projectunion.presentation.screens.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ErrorField(error: String) {
	Text(
		text = error,
		modifier = Modifier.fillMaxWidth(),
		style = TextStyle(
			color = MaterialTheme.colors.error,
			fontSize = 14.sp
		)
	)
}