package com.example.projectunion.presentation.components.password_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R
import com.example.projectunion.common.Constants.PASSWORD_PLACEHOLDER
import com.example.projectunion.presentation.components.error_field.ErrorField

@Composable
fun Password(
	password: String,
	error: String?,
	focusManager: FocusManager,
	onPasswordChanged: (String) -> Unit
) {
	var passwordVisibility by remember { mutableStateOf(false) }
	val iconVisibility = if (passwordVisibility)
		Icons.Default.Visibility
	else
		Icons.Default.VisibilityOff

	TextField(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(align = Alignment.CenterVertically)
			.height(57.dp),
		value = password,
		onValueChange = { value -> onPasswordChanged(value) },
		placeholder = {
			Text(
				text = PASSWORD_PLACEHOLDER,
				style = MaterialTheme.typography.subtitle1
			)
		},
		trailingIcon = {
			IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
				Icon(
					imageVector = iconVisibility,
					contentDescription = "Visibility icon"
				)
			}
		},
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Password,
			imeAction = ImeAction.Done
		),
		keyboardActions = KeyboardActions(
			onDone = { focusManager.clearFocus() }
		),
		visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
		singleLine = true,
		textStyle = MaterialTheme.typography.subtitle1,
		shape = RoundedCornerShape(10.dp),
		colors = TextFieldDefaults.textFieldColors(
			disabledTextColor = Color.Transparent,
			backgroundColor = colorResource(id = R.color.app_background),
			cursorColor = Color.Black,
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