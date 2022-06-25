package com.example.projectunion.presentation.components.project_item_information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
				style = MaterialTheme.typography.h6,
				overflow = TextOverflow.Ellipsis,
				letterSpacing= 0.2.sp,
				lineHeight = 25.sp
			)
		}
		Spacer(modifier = Modifier.height(5.dp))
		description?.let {
			Text(
				text = it,
				maxLines = maxLines,
				style = MaterialTheme.typography.body1,
				overflow = TextOverflow.Ellipsis,
				letterSpacing= 0.2.sp,
				lineHeight = 25.sp
			)
		}
	}
}