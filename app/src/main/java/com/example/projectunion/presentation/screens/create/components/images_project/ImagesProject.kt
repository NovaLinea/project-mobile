package com.example.projectunion.presentation.screens.create.components.images_project

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImagesProject(images: MutableList<Uri>) {
	Row(
		modifier = Modifier
			.padding(horizontal = 5.dp)
			.fillMaxWidth()
	) {
		images.forEach { imageUri ->
			Card(
				modifier = Modifier
					.padding(horizontal = 5.dp)
					.height(70.dp)
					.width(100.dp),
				backgroundColor = Color.White,
				elevation = 5.dp,
				shape = RoundedCornerShape(10.dp)
			) {
				Image(
					painter = rememberImagePainter(data = imageUri),
					contentDescription = null,
					contentScale = ContentScale.Crop,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
	}
}