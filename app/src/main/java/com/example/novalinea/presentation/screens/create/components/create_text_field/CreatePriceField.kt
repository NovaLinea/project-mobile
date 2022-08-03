package com.example.novalinea.presentation.screens.create.components.create_text_field

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.PRICE_PROJECT_PLACEHOLDER
import com.example.novalinea.presentation.components.close_button.CloseButton

@Composable
fun CreatePriceField(
	value: String,
	onValueChange: (String) -> Unit,
	focusManager: FocusManager
) {
	Card(
		modifier = Modifier
			.height(45.dp)
			.fillMaxWidth(),
		elevation = 0.dp,
		shape = RoundedCornerShape(10.dp),
		backgroundColor = colorResource(id = R.color.app_background)
	) {
		Row(
			modifier = Modifier.fillMaxSize(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth(0.75f)
					.fillMaxHeight(),
				contentAlignment = Alignment.CenterStart
			) {
				Row(
					modifier = Modifier.fillMaxSize(),
					verticalAlignment = Alignment.CenterVertically
				) {
					Box(
						modifier = Modifier
							.padding(start = 10.dp)
							.size(18.dp)
					) {
						Icon(
							painter = painterResource(id = R.drawable.ic_ruble),
							contentDescription = null,
							tint = Color.DarkGray
						)
					}

					BasicTextField(
						modifier = Modifier
							.padding(start = 10.dp)
							.fillMaxWidth(),
						value = value,
						onValueChange = { value ->
							onValueChange(value)
						},
						singleLine = true,
						textStyle = MaterialTheme.typography.body1,
						keyboardOptions = KeyboardOptions(
							keyboardType = KeyboardType.Number,
							imeAction = ImeAction.Done
						),
						keyboardActions = KeyboardActions(
							onDone = { focusManager.clearFocus() }
						),
						decorationBox = { innerTextField ->
							Row() {
								if (value.isEmpty()) {
									Text(
										text = PRICE_PROJECT_PLACEHOLDER,
										style = MaterialTheme.typography.body1,
										color = Color.Gray,
									)
								}
							}
							innerTextField()
						}
					)
				}
			}

			if (value.isNotEmpty()) {
				Card(
					modifier = Modifier
						.padding(end = 10.dp)
						.size(20.dp),
					backgroundColor = Color.LightGray,
					shape = CircleShape,
					elevation = 0.dp
				) {
					Box(
						modifier = Modifier.padding(3.dp)
					) {
						CloseButton {
							onValueChange("")
						}
					}
				}
			}
		}
	}
}