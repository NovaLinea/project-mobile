package com.example.projectunion.presentation.components.button_action

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.projectunion.R

@Composable
fun ButtonActionIcon(
	icon: ImageVector,
	enabled: Boolean = true,
	onClicked: () -> Unit
) {
	Button(
		modifier = Modifier.width(50.dp),
		onClick = { onClicked() },
		colors = ButtonDefaults.buttonColors(
			backgroundColor = colorResource(id = R.color.app_blue),
			contentColor = Color.White
		),
		shape = RoundedCornerShape(10.dp),
		enabled = enabled
	) {
		Icon(
			modifier = Modifier.padding(vertical = 4.dp),
			imageVector = icon,
			contentDescription = null
		)
	}
}