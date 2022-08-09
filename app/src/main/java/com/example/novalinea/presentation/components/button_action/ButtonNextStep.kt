package com.example.novalinea.presentation.components.button_action

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R

@Composable
fun ButtonNextStep(
	enabled: Boolean,
	onClickNext: () -> Unit
) {
	Button(
		modifier = Modifier.size(50.dp),
		onClick = { onClickNext() },
		colors = ButtonDefaults.buttonColors(
			backgroundColor = colorResource(id = R.color.app_blue),
			contentColor = Color.White
		),
		shape = CircleShape,
		enabled = enabled
	) {
		Icon(
			imageVector = Icons.Default.ArrowForward,
			contentDescription = null
		)
	}
}