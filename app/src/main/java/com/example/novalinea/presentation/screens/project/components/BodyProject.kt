package com.example.novalinea.presentation.screens.project.components

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
	description: String?
) {
	Column() {
		title?.let {
			Text(
				text = it,
				style = MaterialTheme.typography.h5,
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}

		if (description != null && description.isNotEmpty()) {
			Spacer(modifier = Modifier.height(10.dp))
			Text(
				text = description,
				style = MaterialTheme.typography.body2,
				overflow = TextOverflow.Ellipsis,
				letterSpacing = 0.2.sp,
				lineHeight = 25.sp
			)
		}
	}
}