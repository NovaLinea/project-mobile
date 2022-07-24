package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.image_painter.ImagePainter

@Composable
fun SliderImagesProject(
	images: List<String>
) {
	if (images.isNotEmpty()) {
		images.forEach { image ->
			Box(
				modifier = Modifier
					.height(250.dp)
					.fillMaxWidth(),
				contentAlignment = Alignment.Center,
			) {
				ImagePainter(
					imageUrl = image,
					onClick = {}
				)
			}
			Spacer(modifier = Modifier.height(10.dp))
		}
	}
}
