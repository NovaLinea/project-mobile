package com.example.projectunion.presentation.screens.project.components

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
					fontSize = 19.sp
				),
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}

		if (description != null && description.isNotEmpty()) {
			Spacer(modifier = Modifier.height(3.dp))
			Text(
				text = description,
				style = TextStyle(
					fontFamily = OpenSans,
					fontWeight = FontWeight.W400,
					fontSize = 16.sp
				),
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}
	}
}