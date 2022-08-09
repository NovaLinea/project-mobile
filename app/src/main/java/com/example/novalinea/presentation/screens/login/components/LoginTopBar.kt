package com.example.novalinea.presentation.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.close_button.CloseButton

@Composable
fun LoginTopBar(
	onClickClose: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
	) {
		Box(
			modifier = Modifier
				.padding(end = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterEnd
		) {
			CloseButton {
				onClickClose()
			}
		}
	}
}