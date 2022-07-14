package com.example.projectunion.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.projectunion.R

@Composable
fun CreatePriceField(
	value: String,
	placeholder: String,
	onValueChange: (String) -> Unit,
	textStyle: TextStyle,
	focusManager: FocusManager
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		BasicTextField(
			modifier = Modifier.width(90.dp),
			value = value,
			onValueChange = { value -> onValueChange(value) },
			singleLine = true,
			textStyle = textStyle,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number,
				imeAction = ImeAction.Next
			),
			keyboardActions = KeyboardActions(
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
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

		if (value.isNotEmpty()) {
			Box(
				modifier = Modifier.size(18.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_ruble),
					contentDescription = null,
					tint = Color.DarkGray
				)
			}
		}
	}
}