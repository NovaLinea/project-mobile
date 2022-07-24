package com.example.novalinea.presentation.screens.project.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.novalinea.presentation.components.animated_shimmer.ShimmerLine
import com.example.novalinea.presentation.components.animated_shimmer.ShimmerPhoto

@Composable
fun ShimmerLoaderProject() {
	val brush = AnimatedShimmer()

	Column(
		modifier = Modifier
			.padding(horizontal = 15.dp, vertical = 10.dp)
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
				ShimmerLine(brush, 100)
			}
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
		ShimmerLine(brush, 225)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 275)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 250)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 300)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 325)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 275)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 225)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 300)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 350)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 325)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 275)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 325)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 300)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 250)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 325)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 275)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 300)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 350)
		Spacer(modifier = Modifier.height(20.dp))
		ShimmerLine(brush, 275)
		Spacer(modifier = Modifier.height(10.dp))
	}
}