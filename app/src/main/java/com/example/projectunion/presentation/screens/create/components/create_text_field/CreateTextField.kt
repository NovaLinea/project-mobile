package com.example.projectunion.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CreateTextField(
	value: String,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onValueChange: (String) -> Unit,
	textStyle: TextStyle
) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		BasicTextField(
			value = value,
			onValueChange = { value -> onValueChange(value) },
			singleLine = false,
			modifier = Modifier.fillMaxWidth(),
			textStyle = textStyle
		)

		if (isPlaceholderVisible) {
			Text(
				text = placeholder,
				color = Color.DarkGray,
				style = textStyle
			)
		}
	}
}