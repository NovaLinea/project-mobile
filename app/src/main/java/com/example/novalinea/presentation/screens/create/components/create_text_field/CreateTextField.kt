package com.example.novalinea.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.Row
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
	onValueChange: (String) -> Unit,
	textStyle: TextStyle
) {
	BasicTextField(
		value = value,
		onValueChange = { value -> onValueChange(value) },
		singleLine = false,
		modifier = Modifier.fillMaxWidth(),
		textStyle = textStyle,
		decorationBox = { innerTextField ->
			Row() {
				if (value.isEmpty()) {
					Text(
						text = placeholder,
						style = textStyle,
						color = Color.Gray
					)
				}
			}
			innerTextField()
		}
	)
}