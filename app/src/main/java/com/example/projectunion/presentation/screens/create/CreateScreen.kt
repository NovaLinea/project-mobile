package com.example.projectunion.presentation.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.presentation.screens.create.CreateViewModel


@Composable
fun CreateScreen(
	typeProject: String,
	navController: NavController,
	viewModel: CreateViewModel = hiltViewModel()
) {
	Scaffold(
		topBar = { TopBar() },
	) {
		Text(typeProject)
	}
}

@Composable
fun TopBar() {
	TopAppBar(
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Text(
				text = CREATE_SCREEN,
				style = TextStyle(
					color = Color.Black,
					fontWeight = FontWeight.W600,
					fontSize = 18.sp
				)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun prevCreateScreen() {
	CreateScreen("sale", rememberNavController())
}