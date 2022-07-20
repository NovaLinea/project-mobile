package com.example.projectunion.presentation.screens.create.components

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.close_button.CloseButton
import com.example.projectunion.presentation.components.image_painter.ImagePainter

@Composable
fun ImagesProject(
	images: List<Uri>,
	onDeleteImage: (Int) -> Unit
) {
	val listState = rememberLazyListState()

	LazyRow(
		state = listState,
		modifier = Modifier
			.padding(horizontal = 5.dp)
			.fillMaxWidth(),
		contentPadding = PaddingValues(horizontal = 5.dp),
		horizontalArrangement = Arrangement.spacedBy(5.dp)
	) {
		itemsIndexed(
			items = images,
			key = { index, _ ->
				index
			}
		) { index, imageUri ->
			Card(
				modifier = Modifier
					.height(70.dp)
					.width(100.dp),
				backgroundColor = Color.White,
				elevation = 5.dp,
				shape = RoundedCornerShape(10.dp)
			) {
				ImagePainter(
					imageUrl = imageUri,
					shape = 10f,
					onClick = {}
				)
				Box(
					modifier = Modifier.fillMaxWidth(),
					contentAlignment = Alignment.TopEnd
				) {
					Card(
						modifier = Modifier
							.padding(3.dp)
							.height(15.dp)
							.width(15.dp),
						backgroundColor = Color.Red,
						elevation = 0.dp,
						shape = RoundedCornerShape(10.dp)
					) {
						Box(
							modifier = Modifier.padding(2.dp)
						) {
							CloseButton {
								onDeleteImage(index)
							}
						}
					}
				}
			}
		}
	}
}