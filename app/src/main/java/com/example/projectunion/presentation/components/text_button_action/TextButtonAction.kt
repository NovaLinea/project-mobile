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
	onClicked: () -> Unit
) {
	TextButton(onClick = { onClicked() }) {
		Text(
			text = title,
			style = TextStyle(
				fontSize = 16.sp,
				color = Color.Blue
			)
		)
	}
}