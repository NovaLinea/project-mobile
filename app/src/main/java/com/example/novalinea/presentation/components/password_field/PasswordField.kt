package com.example.novalinea.presentation.components.password_field

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.PASSWORD_PLACEHOLDER
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.ui.theme.OpenSans

@Composable
fun Password(
	password: String,
	error: String?,
	focusManager: FocusManager,
	onChangePassword: (String) -> Unit
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
		onValueChange = { value -> onChangePassword(value) },
		placeholder = {
			Text(
				text = PASSWORD_PLACEHOLDER,
				style = TextStyle(
					color = Color.DarkGray,
					fontFamily = OpenSans,
					fontSize = 17.sp,
					fontWeight = FontWeight.W500
				)
			)
		},
		trailingIcon = {
			IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
				Icon(
					imageVector = iconVisibility,
					contentDescription = null
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
		textStyle = TextStyle(
			color = Color.Black,
			fontFamily = OpenSans,
			fontSize = 17.sp,
			fontWeight = FontWeight.W500
		),
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