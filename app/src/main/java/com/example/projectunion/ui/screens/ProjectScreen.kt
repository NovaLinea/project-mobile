package com.example.projectunion.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectunion.navigation.ProjectNavRoute

@Composable
fun ProjectScreen(navController: NavController, projectID: Int) {
	Scaffold(
		topBar = {
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
						contentDescription = "Array back icon",
						modifier = Modifier.clickable {
							navController.navigate(
								route = ProjectNavRoute.Main.route
							)
						}
					)
					Icon(
						imageVector = Icons.Default.MoreVert,
						contentDescription = "Menu icon"
					)
				}
			}
		}
	) {
		Text(text = "Project #$projectID")
	}
}