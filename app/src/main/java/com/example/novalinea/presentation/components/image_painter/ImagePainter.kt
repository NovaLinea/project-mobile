package com.example.novalinea.presentation.components.image_painter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.novalinea.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.novalinea.R

@Composable
fun ImagePainter(
	imageUrl: Any?,
	isCircle: Boolean = false,
	shape: Float = 0f,
	errorPhoto: Int = R.drawable.ic_photo,
	padding: Int = 15,
	onClick: () -> Unit
) {
	val painter = rememberImagePainter(
		data = imageUrl,
		builder = {
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
		val brush = AnimatedShimmer()
		var radius = 5
		if (isCircle)
			radius = 100

		Spacer(modifier = Modifier
			.fillMaxSize()
			.clip(RoundedCornerShape(radius.dp))
			.background(brush)
		)
	}

	if (painterState is AsyncImagePainter.State.Error) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.LightGray),
			contentAlignment = Alignment.Center
		) {
			Icon(
				modifier = Modifier.padding(padding.dp),
				painter = painterResource(id = errorPhoto),
				contentDescription = null
			)
		}
	}
}