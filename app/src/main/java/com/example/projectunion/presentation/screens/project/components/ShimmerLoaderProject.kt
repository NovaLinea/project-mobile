package com.example.projectunion.presentation.screens.project.components

import androidx.compose.runtime.Composable
import com.example.projectunion.presentation.components.animated_shimmer.AnimatedShimmer
import com.example.projectunion.presentation.screens.home.components.ShimmerItemProject

@Composable
fun ShimmerLoaderProject() {
	val brush = AnimatedShimmer()

	ShimmerItemProject(brush)
}