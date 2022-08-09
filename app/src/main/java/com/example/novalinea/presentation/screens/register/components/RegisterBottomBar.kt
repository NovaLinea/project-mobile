package com.example.novalinea.presentation.screens.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.common.Constants.BUTTON_LOGIN
import com.example.novalinea.common.Constants.BUTTON_REGISTER
import com.example.novalinea.presentation.components.button_action.ButtonActionText
import com.example.novalinea.presentation.components.text_button_action.TextButtonAction

@Composable
fun RegisterBottomBar(
	enabledRegister: Boolean,
	onClickLogin: () -> Unit,
	onClickRegister: () -> Unit
) {
	BottomNavigation(
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Row(
			modifier = Modifier
				.padding(horizontal = 10.dp)
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			TextButtonAction(title = BUTTON_LOGIN) {
				onClickLogin()
			}

			ButtonActionText(
				BUTTON_REGISTER,
				enabled = enabledRegister
			) {
				onClickRegister()
			}
		}
	}
}
