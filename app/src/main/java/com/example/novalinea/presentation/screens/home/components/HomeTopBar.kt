package com.example.novalinea.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.novalinea.R

@Composable
fun HomeTopBar() {
	TopAppBar(
		modifier = Modifier.fillMaxWidth(),
		title = {
			Box(
				modifier = Modifier.fillMaxWidth(),
				Alignment.Center
			) {
				Image(
					painter = painterResource(id = R.drawable.ic_logo_name),
					contentDescription = null
				)
			}
		},
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}