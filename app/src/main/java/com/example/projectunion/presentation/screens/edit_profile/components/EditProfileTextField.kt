package com.example.projectunion.presentation.screens.edit_profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EditProfileTextField(
	value: String,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onValueChange: (String) -> Unit,
	singleLine: Boolean
) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		BasicTextField(
			value = value,
			onValueChange = { value -> onValueChange(value) },
			singleLine = singleLine,
			modifier = Modifier.fillMaxWidth(),
			textStyle = MaterialTheme.typography.body1
		)

		if (isPlaceholderVisible) {
			Text(
				text = placeholder,
				color = Color.DarkGray,
				style = MaterialTheme.typography.body1
			)
		}
	}
}