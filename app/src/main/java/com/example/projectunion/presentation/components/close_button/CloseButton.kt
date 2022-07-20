package com.example.projectunion.presentation.components.close_button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CloseButton(onClicked: () -> Unit) {
	IconButton(
		onClick = { onClicked() }
	) {
		Icon(
			imageVector = Icons.Default.Close,
			contentDescription = null,
			tint = Color.Black
		)
	}
}