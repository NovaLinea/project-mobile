package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.projectunion.R
import com.example.projectunion.presentation.components.loader.Loader

@Composable
fun ImagePainter(
	imageUrl: Any,
	isCircle: Boolean = false,
	shape: Float = 0f,
	onClick: () -> Unit,
) {
	val painter = rememberImagePainter(
		data = imageUrl,
		builder = {
			//placeholder(R.drawable.ic_photo)
			error(R.drawable.ic_photo)
			crossfade(300)
			scale(Scale.FILL)
			if (isCircle)
				transformations(CircleCropTransformation())
			if (shape != 0f) {
				transformations(RoundedCornersTransformation(shape))
			}
		}
	)
	val painterState = painter.state

	Image(
		painter = painter,
		contentDescription = null,
		contentScale = ContentScale.Crop,
		modifier = Modifier
			.fillMaxSize()
			.clickable { onClick() }
	)

	if (painterState is AsyncImagePainter.State.Loading) {
		Loader()
	}
}