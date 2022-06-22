package com.example.projectunion.presentation.components.text_button_action

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextButtonAction(
	title: String,
	color: Color = Color.Blue,
	onClick: () -> Unit
) {
	TextButton(onClick = { onClick() }) {
		Text(
			text = title,
			style = TextStyle(
				fontSize = 16.sp,
				color = color
			)
		)
	}
}