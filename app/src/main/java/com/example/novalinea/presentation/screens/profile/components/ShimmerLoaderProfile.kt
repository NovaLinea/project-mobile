package com.example.novalinea.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.novalinea.presentation.components.animated_shimmer.ShimmerBlock
import com.example.novalinea.presentation.components.animated_shimmer.ShimmerLine
import com.example.novalinea.presentation.components.animated_shimmer.ShimmerPhotoCircle

@Composable
fun ShimmerLoaderProfile() {
	val brush = AnimatedShimmer()

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.White),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		ShimmerPhotoCircle(brush, 70)

		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 100)

		Spacer(modifier = Modifier.height(15.dp))
		ShimmerLine(brush, 200)
		Spacer(modifier = Modifier.height(10.dp))
		ShimmerLine(brush, 125)

		Spacer(modifier = Modifier.height(20.dp))
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 30.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceAround
		) {
			ShimmerBlock(brush, 50, 35)
			ShimmerBlock(brush, 50, 35)
			ShimmerBlock(brush, 50, 35)
		}

		Spacer(modifier = Modifier.height(20.dp))
		ShimmerBlock(brush, 150, 40, 10)
		Spacer(modifier = Modifier.height(20.dp))
	}
}