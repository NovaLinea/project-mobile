package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectunion.presentation.screens.home.components.ImagePainter

@Composable
fun AvatarUser(photo: String?) {
	Box(
		modifier = Modifier
			.width(70.dp)
			.height(70.dp),
		contentAlignment = Alignment.Center
	) {
		if (photo != null) {
			ImagePainter(
				imageUrl = photo,
				isCircle = true,
				onClick = {}
			)
		}
		else {
			Card(
				modifier = Modifier.fillMaxSize(),
				shape = CircleShape,
				elevation = 0.dp,
				backgroundColor = Color.LightGray
			) {
				Icon(
					modifier = Modifier.padding(15.dp),
					imageVector = Icons.Default.Person,
					contentDescription = null
				)
			}
		}
	}
}