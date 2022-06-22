package com.example.projectunion.presentation.screens.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer() {
	val shimmerColors = listOf(
		Color.LightGray.copy(alpha = 0.6f),
		Color.LightGray.copy(alpha = 0.2f),
		Color.LightGray.copy(alpha = 0.6f)
	)

	val transition = rememberInfiniteTransition()
	val translateAnimation = transition.animateFloat(
		initialValue = 0f,
		targetValue = 1000f,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 1000,
				easing = FastOutSlowInEasing
			)
		)
	)

	val brush = Brush.linearGradient(
		colors = shimmerColors,
		start = Offset.Zero,
		end = Offset(x = translateAnimation.value, y = translateAnimation.value)
	)

	ShimmerGridItem(brush)
}


@Composable
fun ShimmerGridItem(brush: Brush) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 10.dp),
		backgroundColor = Color.White,
	) {
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					Spacer(modifier = Modifier
						.size(25.dp)
						.clip(RoundedCornerShape(10.dp))
						.background(brush)
					)
					Spacer(modifier = Modifier
						.width(100.dp)
						.height(20.dp)
						.clip(RoundedCornerShape(10.dp))
						.background(brush)
					)
				}

				Spacer(modifier = Modifier
					.width(80.dp)
					.height(20.dp)
					.clip(RoundedCornerShape(10.dp))
					.background(brush)
				)
			}
		}
	}
}