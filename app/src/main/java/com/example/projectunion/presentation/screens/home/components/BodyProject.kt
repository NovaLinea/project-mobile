package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.presentation.ui.theme.*

@Composable
fun BodyProject(
	title: String?,
	description: String?
) {
	Column() {
		title?.let {
			Text(
				text = it,
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W600,
					fontSize = 17.sp
				),
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}

		if (description != null && description.isNotEmpty()) {
			Spacer(modifier = Modifier.height(5.dp))
			Text(
				text = description,
				maxLines = 5,
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W400,
					fontSize = 15.sp
				),
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}
	}
}