package com.example.projectunion.presentation.components.button_action

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.R

@Composable
fun ButtonActionText(
	title: String,
	enabled: Boolean = true,
	onClicked: () -> Unit
) {
	Button(
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
			modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
			style = MaterialTheme.typography.button
		)
	}
}