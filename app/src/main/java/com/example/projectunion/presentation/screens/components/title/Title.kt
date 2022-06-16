package com.example.projectunion.presentation.screens.components.title

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String) {
	Text(
		text = title,
		modifier = Modifier
			.padding(bottom = 30.dp),
		style = TextStyle(
			fontSize = 25.sp,
			fontWeight = FontWeight.Bold
		)
	)
}