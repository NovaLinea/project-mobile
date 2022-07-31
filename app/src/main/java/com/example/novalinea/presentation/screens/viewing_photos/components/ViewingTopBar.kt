package com.example.novalinea.presentation.screens.viewing_photos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.novalinea.presentation.components.icon_button.IconButtonAction

@Composable
fun ViewingTopBar(
	onClickBack: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(55.dp)
			.fillMaxWidth()
			.background(Color.Black)
	) {
		Box(
			modifier = Modifier
				.padding(start = 5.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.CenterStart
		) {
			IconButtonAction(
				icon = Icons.Default.ArrowBack,
				tint = Color.White
			) {
				onClickBack()
			}
		}
	}
}