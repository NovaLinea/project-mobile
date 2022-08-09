package com.example.novalinea.presentation.components.login_field

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.novalinea.common.Constants.LOGIN_PLACEHOLDER
import com.example.novalinea.presentation.components.error_field.ErrorField
import com.example.novalinea.presentation.ui.theme.OpenSans
import com.example.novalinea.R

@Composable
fun Login(
	name: String,
	error: String?,
	focusManager: FocusManager,
	onNameChanged: (String) -> Unit
) {
	TextField(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(align = Alignment.CenterVertically)
			.height(57.dp),
		value = name,
		onValueChange = { value -> onNameChanged(value) },
		placeholder = {
			Text(
				text = LOGIN_PLACEHOLDER,
				style = TextStyle(
					color = Color.DarkGray,
					fontFamily = OpenSans,
					fontSize = 17.sp,
					fontWeight = FontWeight.W500
				)
			)
		},
		keyboardOptions = KeyboardOptions(
			imeAction = ImeAction.Next
		),
		keyboardActions = KeyboardActions(
			onNext = { focusManager.moveFocus(FocusDirection.Down) }
		),
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