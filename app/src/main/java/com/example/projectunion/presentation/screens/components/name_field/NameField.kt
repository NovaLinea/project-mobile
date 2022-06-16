package com.example.projectunion.presentation.screens.components.name_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R
import com.example.projectunion.common.Constants.NAME_PLACEHOLDER
import com.example.projectunion.presentation.screens.components.ErrorField

@Composable
fun Name(
	name: String,
	error: String?,
	focusManager: FocusManager,
	onNameChanged: (String) -> Unit
) {
	TextField(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(align = Alignment.CenterVertically)
			.height(68.dp),
		value = name,
		onValueChange = { value -> onNameChanged(value) },
		placeholder = { Text(NAME_PLACEHOLDER) },
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Email,
			imeAction = ImeAction.Next
		),
		keyboardActions = KeyboardActions(
			onNext = { focusManager.moveFocus(FocusDirection.Down) }
		),
		singleLine = true,
		textStyle = TextStyle(
			fontSize = 18.sp,
			color = Color.Black
		),
		shape = RoundedCornerShape(10.dp),
		colors = TextFieldDefaults.textFieldColors(
			textColor = Color.Gray,
			disabledTextColor = Color.Transparent,
			backgroundColor = colorResource(id = R.color.app_background),
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
			disabledIndicatorColor = Color.Transparent
		),
		isError = error != null
	)

	error?.let {
		ErrorField(error = it)
	}
}