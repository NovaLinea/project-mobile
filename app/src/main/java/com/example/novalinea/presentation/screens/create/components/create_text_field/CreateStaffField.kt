package com.example.novalinea.presentation.screens.create.components.create_text_field

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.novalinea.R
import com.example.novalinea.common.Constants.MAX_STAFF_LENGTH
import com.example.novalinea.common.Constants.STAFF_PLACEHOLDER
import com.example.novalinea.presentation.components.icon_button.IconButtonAction

@SuppressLint("UnrememberedMutableState")
@Composable
fun CreateStaffField(
	addStaff: (String) -> Unit,
) {
	var staff = remember { mutableStateOf("") }

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
					.padding(start = 12.dp)
					.fillMaxWidth(0.85f)
					.fillMaxHeight(),
				contentAlignment = Alignment.CenterStart
			) {
				BasicTextField(
					value = staff.value,
					onValueChange = { value ->
						if (value.length <= MAX_STAFF_LENGTH)
							staff.value = value
					},
					singleLine = true,
					modifier = Modifier.fillMaxWidth(),
					textStyle = MaterialTheme.typography.body2,
					keyboardOptions = KeyboardOptions(
						imeAction = ImeAction.Done
					),
					keyboardActions = KeyboardActions(
						onDone = {
							addStaff(staff.value)
							staff.value = ""
						}
					),
					decorationBox = { innerTextField ->
						Row() {
							if (staff.value.isEmpty()) {
								Text(
									text = STAFF_PLACEHOLDER,
									style = MaterialTheme.typography.body2,
									color = Color.Gray
								)
							}
						}
						innerTextField()
					}
				)
			}

			if (staff.value.isNotEmpty()) {
				Card(
					modifier = Modifier
						.size(40.dp)
						.padding(3.dp),
					elevation = 0.dp,
					shape = CircleShape,
					backgroundColor = colorResource(id = R.color.app_blue)
				) {
					Box(
						modifier = Modifier.padding(7.dp),
						contentAlignment = Alignment.Center
					) {
						IconButtonAction(
							icon = Icons.Default.ArrowDownward,
							tint = Color.White,
							onClick = {
								addStaff(staff.value)
								staff.value = ""
							}
						)
					}
				}
			}
		}
	}
}