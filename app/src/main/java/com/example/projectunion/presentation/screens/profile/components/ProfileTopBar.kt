package com.example.projectunion.presentation.screens.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.common.Constants.PROFILE_SCREEN

@Composable
fun ProfileTopBar(
	navController: NavController
) {
	TopAppBar(
		title = {
			Box(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = PROFILE_SCREEN,
					style = MaterialTheme.typography.h6
				)
			}
		},
		navigationIcon = {
		   IconButton(
			   onClick = {
				   navController.popBackStack()
			   }
		   ) {
			   Icon(
				   imageVector = Icons.Default.ArrowBack,
				   contentDescription = null,
				   tint = Color.Black
			   )
		   }
	   },
		backgroundColor = Color.White,
		elevation = 0.dp
	)
}