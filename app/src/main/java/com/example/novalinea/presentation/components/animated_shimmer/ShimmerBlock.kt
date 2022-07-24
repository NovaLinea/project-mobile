package com.example.novalinea.presentation.components.animated_shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerBlock(brush: Brush, width: Int, height: Int, clip: Int = 5) {
	Spacer(modifier = Modifier
		.width(width.dp)
		.height(height.dp)
		.clip(RoundedCornerShape(clip.dp))
		.background(brush)
	)
}