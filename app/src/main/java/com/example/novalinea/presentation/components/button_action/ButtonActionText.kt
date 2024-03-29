package com.example.novalinea.presentation.components.button_action

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R

@Composable
fun ButtonActionText(
	title: String,
	enabled: Boolean = true,
	isMaxWidth: Boolean = false,
	onClicked: () -> Unit
) {
	Button(
		modifier = if (isMaxWidth) Modifier.fillMaxWidth() else Modifier,
		onClick = { onClicked() },
		colors = ButtonDefaults.buttonColors(
			backgroundColor = colorResource(id = R.color.app_blue),
			contentColor = Color.White
		),
		shape = RoundedCornerShape(10.dp),
		enabled = enabled
	) {
		Text(
			text = title,
			modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
			style = MaterialTheme.typography.button
		)
	}
}