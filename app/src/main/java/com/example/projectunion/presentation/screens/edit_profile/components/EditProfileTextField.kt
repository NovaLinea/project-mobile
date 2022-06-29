package com.example.projectunion.presentation.screens.edit_profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun EditProfileTextField(
	value: String,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onValueChange: (String) -> Unit,
	singleLine: Boolean,
	onSave: () -> Unit
) {
	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		BasicTextField(
			value = value,
			onValueChange = { value -> onValueChange(value) },
			singleLine = singleLine,
			modifier = Modifier.fillMaxWidth(),
			textStyle = MaterialTheme.typography.body1,
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done
			),
			keyboardActions = KeyboardActions(
				onDone = { onSave() }
			),
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