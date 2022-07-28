package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.image_painter.ImagePainter

@Composable
fun SliderImagesProject(
	images: List<String>
) {
	val listState = rememberLazyListState()

	if (images.isNotEmpty()) {
		LazyRow(
			state = listState,
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			items(
				items = images,
				key = { image ->
					image.hashCode()
				}
			) { image ->
				Card(
					modifier = Modifier
						.height(70.dp)
						.width(100.dp),
					backgroundColor = Color.White,
					elevation = 5.dp,
					shape = RoundedCornerShape(10.dp)
				) {
					ImagePainter(
						imageUrl = image,
						shape = 10f,
						onClick = {}
					)
				}
			}
		}
	}
}
