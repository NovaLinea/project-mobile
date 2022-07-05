package com.example.projectunion.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.projectunion.presentation.components.animated_shimmer.ShimmerLine
import com.example.projectunion.presentation.components.animated_shimmer.ShimmerPhoto

@Composable
fun ShimmerLoaderProjects() {
	val brush = AnimatedShimmer()
	Column() {
		repeat(5) {
			ShimmerItemProject(brush)
		}
	}
}

@Composable
fun ShimmerItemProject(brush: Brush) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		backgroundColor = Color.White,
	) {
		Column(
			modifier = Modifier
				.padding(20.dp)
				.fillMaxWidth()
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					ShimmerPhoto(brush, 20)
					Spacer(modifier = Modifier.width(10.dp))
					ShimmerLine(brush, 125)
				}
				ShimmerLine(brush, 50)
			}
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 250)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 300)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 350)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 200)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 275)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 175)
			Spacer(modifier = Modifier.height(20.dp))
			ShimmerLine(brush, 250)
			Spacer(modifier = Modifier.height(10.dp))
		}
	}
}