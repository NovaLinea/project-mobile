package com.example.projectunion.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R
import com.example.projectunion.common.Constants

@Composable
fun CreatePriceField(
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
			singleLine = true,
			modifier = Modifier.fillMaxWidth(),
			textStyle = textStyle,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number,
			),
		)

		if (isPlaceholderVisible) {
			Text(
				text = placeholder,
				color = Color.DarkGray,
				style = textStyle
			)
		}

		/*Icon(
			imageVector = Icons.Filled.AttachMoney,
			contentDescription = "Money icon"
		)*/
	}
}