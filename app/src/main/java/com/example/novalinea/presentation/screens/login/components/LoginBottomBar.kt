package com.example.novalinea.presentation.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants
import com.example.novalinea.presentation.components.button_action.ButtonActionText
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction

@Composable
fun LoginBottomBar(
	enabled: Boolean,
	onClickRegister: () -> Unit,
	onClickLogin: () -> Unit
) {
	Row(
		modifier = Modifier
			.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		TextButtonAction(title = Constants.BUTTON_REGISTER) {
			onClickRegister()
		}

		ButtonActionText(
			Constants.BUTTON_LOGIN,
			enabled = enabled
		) {
			onClickLogin()
		}
	}
}