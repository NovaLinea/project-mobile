package com.example.projectunion.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CreatePriceField(
	value: String,
	placeholder: String,
	onValueChange: (String) -> Unit,
	textStyle: TextStyle
) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		BasicTextField(
			value = value,
			onValueChange = { value -> onValueChange(value) },
			singleLine = true,
			modifier = Modifier.fillMaxWidth(),
			textStyle = textStyle,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number,
			),
			decorationBox = { innerTextField ->
				Row() {
					if (value.isEmpty()) {
						Text(
							text = placeholder,
							style = textStyle,
							color = Color.DarkGray,
						)
					}
				}
				innerTextField()
			}
		)
	}
}