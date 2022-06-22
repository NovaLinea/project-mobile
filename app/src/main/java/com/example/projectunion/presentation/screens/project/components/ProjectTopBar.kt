package com.example.projectunion.presentation.screens.project.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProjectTopBar(
	navController: NavController
) {
	TopAppBar(
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Row(
			modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Icon(
				imageVector = Icons.Default.ArrowBack,
				contentDescription = null,
				modifier = Modifier.clickable {
					navController.popBackStack()
				}
			)
			Icon(
				imageVector = Icons.Default.MoreVert,
				contentDescription = null
			)
		}
	}
}