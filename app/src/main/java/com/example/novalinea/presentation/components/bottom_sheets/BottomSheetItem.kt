package com.example.novalinea.presentation.components.bottom_sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheetItem(
	title: String,
	icon: ImageVector,
	onClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.background(Color.White)
			.clickable { onClick() },
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		Box(
			modifier = Modifier
				.padding(start = 20.dp)
				.size(20.dp),
			contentAlignment = Alignment.Center
		) {
			Icon(
				modifier = Modifier.fillMaxSize(),
				imageVector = icon,
				contentDescription = null
			)
		}

		Text(
			text = title,
			modifier = Modifier.padding(start = 15.dp),
			style = TextStyle(
				fontSize = 17.sp,
				fontWeight = FontWeight.W500,
				color = Color.Black,
			),
		)
	}
}