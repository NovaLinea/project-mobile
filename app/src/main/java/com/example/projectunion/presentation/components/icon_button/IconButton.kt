package com.example.projectunion.presentation.components.icon_button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButtonAction(
	icon: ImageVector,
	onClick: () -> Unit
) {
	IconButton(
		onClick = { onClick() }
	) {
		Icon(
			imageVector = icon,
			contentDescription = null,
			tint = Color.Black
		)
	}
}