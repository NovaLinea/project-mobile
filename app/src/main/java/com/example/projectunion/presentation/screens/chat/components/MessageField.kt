package com.example.projectunion.presentation.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.projectunion.R
import com.example.projectunion.common.Constants.MESSAGE_FIELD
import com.example.projectunion.presentation.components.icon_button.IconButtonAction

@Composable
fun MessageField(
	value: String,
	onValueChange: (String) -> Unit,
	onSend: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
			.background(colorResource(id = R.color.app_background)),
		contentAlignment = Alignment.CenterStart
	) {
		Card(
			modifier = Modifier
				.padding(horizontal = 10.dp, vertical = 7.dp)
				.fillMaxSize(),
			elevation = 0.dp,
			shape = RoundedCornerShape(20.dp),
			backgroundColor = Color.White
		) {
			Row(
				modifier = Modifier.fillMaxSize(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Box(
					modifier = Modifier
						.padding(start = 12.dp)
						.fillMaxWidth(0.85f)
						.fillMaxHeight(),
					contentAlignment = Alignment.CenterStart
				) {
					BasicTextField(
						modifier = Modifier.fillMaxWidth(),
						value = value,
						onValueChange = { value -> onValueChange(value) },
						singleLine = false,
						textStyle = MaterialTheme.typography.body1,
						keyboardOptions = KeyboardOptions(
							imeAction = ImeAction.None
						),
						decorationBox = { innerTextField ->
							Row() {
								if (value.isEmpty()) {
									Text(
										text = MESSAGE_FIELD,
										color = Color.DarkGray,
										style = MaterialTheme.typography.body1
									)
								}
							}
							innerTextField()
						}
					)
				}

				if (value.isNotEmpty()) {
					Card(
						modifier = Modifier
							.size(40.dp)
							.padding(3.dp),
						elevation = 0.dp,
						shape = CircleShape,
						backgroundColor = colorResource(id = R.color.button_send_message)
					) {
						Box(
							modifier = Modifier
								.padding(7.dp),
							contentAlignment = Alignment.Center
						) {
							IconButtonAction(
								icon = Icons.Default.ArrowUpward,
								tint = Color.White,
								onClick = { onSend() }
							)
						}
					}
				}
			}
		}
	}
}