package com.example.projectunion.presentation.components.animated_shimmer

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
fun ShimmerLine(brush: Brush, width: Int) {
	Spacer(modifier = Modifier
		.width(width.dp)
		.height(8.dp)
		.clip(RoundedCornerShape(10.dp))
		.background(brush)
	)
}