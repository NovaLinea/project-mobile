package com.example.projectunion.presentation.components.search_field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R
import com.example.projectunion.common.Constants.SEARCH_PLACEHOLDER
import com.example.projectunion.presentation.ui.theme.OpenSans

@Composable
fun SearchField(
	value: String,
	onValueChange: (String) -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(45.dp),
		elevation = 3.dp,
		shape = RoundedCornerShape(5.dp)
	) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			Icon(
				modifier = Modifier.padding(start = 10.dp),
				imageVector = Icons.Default.Search,
				contentDescription = null,
				tint = Color.DarkGray
			)

			BasicTextField(
				modifier = Modifier
					.padding(start = 40.dp),
				value = value,
				onValueChange = { value -> onValueChange(value) },
				singleLine = true,
				textStyle = TextStyle(
					color = Color.Black,
					fontFamily = OpenSans,
					fontSize = 16.sp,
					fontWeight = FontWeight.W400
				),
				decorationBox = { innerTextField ->
					Row() {
						if (value.isEmpty()) {
							Text(
								text = SEARCH_PLACEHOLDER,
								style = TextStyle(
									color = Color.DarkGray,
									fontFamily = OpenSans,
									fontSize = 16.sp,
									fontWeight = FontWeight.W400
								)
							)
						}
					}
					innerTextField()
				}
			)
		}
	}

	/*TextField(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(align = Alignment.CenterVertically)
			.height(54.dp),
		value = text,
		onValueChange = { value -> onValueChanged(value) },
		placeholder = {
			Text(
				text = SEARCH_PLACEHOLDER,
				style = TextStyle(
					color = Color.DarkGray,
					fontFamily = OpenSans,
					fontSize = 16.sp,
					fontWeight = FontWeight.W500
				)
			)
		},
		keyboardOptions = KeyboardOptions(
			imeAction = ImeAction.Done
		),
		keyboardActions = KeyboardActions(
			onDone  = {  }
		),
		singleLine = true,
		textStyle = TextStyle(
			color = Color.Black,
			fontFamily = OpenSans,
			fontSize = 16.sp,
			fontWeight = FontWeight.W500
		),
		leadingIcon = {
			Icon(
				imageVector = Icons.Default.Search,
				contentDescription = null,
				tint = Color.DarkGray
			)
		},
		shape = RoundedCornerShape(5.dp),
		colors = TextFieldDefaults.textFieldColors(
			disabledTextColor = Color.Transparent,
			backgroundColor = colorResource(id = R.color.app_background),
			cursorColor = Color.Black,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
			disabledIndicatorColor = Color.Transparent
		)
	)*/
}