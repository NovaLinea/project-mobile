package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImageProject(
	imageUrl: String,
	onClick: () -> Unit
) {
	val painter = rememberImagePainter(data = imageUrl)
	Box(
		modifier = Modifier
			.height(250.dp)
			.fillMaxWidth(),
		contentAlignment = Alignment.Center,
	) {
		Image(
			painter = painter,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxSize()
				.clickable { onClick() }
		)
	}
}