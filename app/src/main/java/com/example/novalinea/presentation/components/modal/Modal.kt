package com.example.novalinea.presentation.components.modal

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_CANCEL
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction

@Composable
fun Modal(
	openDialog: MutableState<Boolean>,
	title: String,
	text: String,
	confirmButton: String,
	onClickTrue: () -> Unit
) {

	if (openDialog.value) {
		AlertDialog(
			onDismissRequest = { openDialog.value = false },
			title = {
				Text(
					text = title,
					style = MaterialTheme.typography.h6
				)
			},
			text = {
				Text(
					text = text,
					style = MaterialTheme.typography.body2,
					color = Color.DarkGray
				)
			},
			confirmButton = {
				TextButtonAction(title = confirmButton) {
					onClickTrue()
					openDialog.value = false
				}
			},
			dismissButton = {
				TextButtonAction(
					title = BUTTON_CANCEL,
					color = Color.Red
				) {
					openDialog.value = false
				}
			},
			backgroundColor = Color.White,
			contentColor = Color.Black,
			shape = RoundedCornerShape(10.dp)
		)
	}
}