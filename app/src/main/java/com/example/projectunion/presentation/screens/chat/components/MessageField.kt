package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun MessageField(
	value: String,
	onValueChange: (String) -> Unit,
	placeholder: String,
	isPlaceholderVisible: Boolean,
	onSend: () -> Unit
) {
	Row(
		modifier = Modifier
			.height(50.dp)
			.padding(horizontal = 10.dp)
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier.fillMaxWidth(0.9f),
			contentAlignment = Alignment.CenterStart
		) {
			BasicTextField(
				value = value,
				onValueChange = { value -> onValueChange(value) },
				singleLine = false,
				modifier = Modifier.fillMaxWidth(),
				textStyle = MaterialTheme.typography.body1,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.None
				),
				//keyboardActions = KeyboardActions(
				//	onDone = { onSend() }
				//),
			)

			if (isPlaceholderVisible) {
				Text(
					text = placeholder,
					color = Color.DarkGray,
					style = MaterialTheme.typography.body1
				)
			}
		}

		IconButtonAction(
			icon = Icons.Default.Send,
			tint = Color.DarkGray,
			onClick = { onSend() }
		)
	}
}