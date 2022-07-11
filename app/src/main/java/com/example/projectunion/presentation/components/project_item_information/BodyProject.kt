package com.example.projectunion.presentation.components.project_item_information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunion.presentation.ui.theme.Mukta
import com.example.projectunion.presentation.ui.theme.Quicksand
import com.example.projectunion.presentation.ui.theme.Raleway

@Composable
fun BodyProject(
	title: String?,
	description: String?,
	maxLines: Int = Int.MAX_VALUE
) {
	Column() {
		title?.let {
			Text(
				text = it,
				style = TextStyle(
					fontFamily = Raleway,
					fontWeight = FontWeight.W600,
					fontSize = 20.sp
				),
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}
		description?.let {
			Spacer(modifier = Modifier.height(3.dp))
			Text(
				text = it,
				maxLines = maxLines,
				style = TextStyle(
					fontFamily = Raleway,
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