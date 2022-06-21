package com.example.projectunion.presentation.screens.create.components.create_top_bar

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
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.common.Constants.MAIN_ROUTE

@Composable
fun CreateTopBar(
	navController: NavController
) {
	TopAppBar(
		title = {
			Box(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = CREATE_SCREEN,
					style = MaterialTheme.typography.h6
				)
			}
		},
		navigationIcon = {
		   IconButton(
			   onClick = {
				   navController.navigate(MAIN_ROUTE)
			   }
		   ) {
			   Icon(
				   imageVector = Icons.Default.ArrowBack,
				   contentDescription = "ArrowBack icon",
				   tint = Color.Black
			   )
		   }
	   },
		backgroundColor = Color.White,
		elevation = 1.dp
	)
}