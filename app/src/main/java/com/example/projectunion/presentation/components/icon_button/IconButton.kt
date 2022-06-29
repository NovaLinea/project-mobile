package com.example.projectunion.presentation.components.icon_button

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonAction(
	icon: ImageVector,
	tint: Color = Color.Black,
	onClick: () -> Unit
) {
	IconButton(
		modifier = Modifier.size(40.dp),
		onClick = { onClick() }
	) {
		Icon(
			imageVector = icon,
			contentDescription = null,
			tint = tint
		)
	}
}