package com.example.projectunion.presentation.components.title

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String) {
	Text(
		text = title,
		style = TextStyle(
			fontSize = 25.sp,
			fontWeight = FontWeight.Bold
		)
	)
}