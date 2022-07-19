package com.example.projectunion.presentation.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.common.Constants.BUTTON_TRY_AGAIN
import com.example.projectunion.presentation.components.text_button_action.TextButtonAction

@Composable
fun Error(
	message: String,
	background: Color = Color.White,
	onClickTryAgain: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(background),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = message,
			style = MaterialTheme.typography.body2,
			color = Color.DarkGray
		)

		TextButtonAction(
			title = BUTTON_TRY_AGAIN,
			color = Color.Blue
		) {
			onClickTryAgain()
		}
	}
}