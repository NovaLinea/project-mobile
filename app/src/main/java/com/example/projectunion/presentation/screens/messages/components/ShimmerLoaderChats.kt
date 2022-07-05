package com.example.projectunion.presentation.screens.messages.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.projectunion.presentation.components.animated_shimmer.ShimmerLine
import com.example.projectunion.presentation.components.animated_shimmer.ShimmerPhotoCircle

@Composable
fun ShimmerLoaderChats() {
	val brush = AnimatedShimmer()
	Column() {
		repeat(5) {
			ShimmerItemChat(brush)
		}
	}
}

@Composable
fun ShimmerItemChat(brush: Brush) {
	Row(
		modifier = Modifier.fillMaxWidth(),
	) {
		Row(
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth()
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				ShimmerPhotoCircle(brush, 55)

				Column(
					modifier = Modifier
						.padding(start = 12.dp, top = 4.dp, end = 10.dp)
						.fillMaxWidth()
				) {
					Row(
						modifier = Modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						ShimmerLine(brush, 100)
						ShimmerLine(brush, 30)
					}
					Spacer(modifier = Modifier.height(10.dp))
					ShimmerLine(brush, 200)
				}
			}
		}
	}
}