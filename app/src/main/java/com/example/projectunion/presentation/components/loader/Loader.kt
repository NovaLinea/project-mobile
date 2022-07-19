package com.example.projectunion.presentation.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.projectunion.R

@Composable
fun Loader(
	background: Color = colorResource(id = R.color.app_background)
) {
	Box(
		modifier = Modifier
			.background(background)
			.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		CircularProgressIndicator(
			color = Color.Gray
		)
	}
}