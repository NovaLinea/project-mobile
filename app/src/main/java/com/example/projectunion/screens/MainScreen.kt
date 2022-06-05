package com.example.projectunion.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.navigation.Screen

@Preview (showBackground = true)
@Composable
fun MainScreen() {
	val navController = rememberNavController()
	val bottomItems = listOf(Screen.Home, Screen.Messages, Screen.Create, Screen.Notifications, Screen.Additionally)

	Scaffold(
		bottomBar = {
			BottomNavigation(
				backgroundColor = Color.White,
				elevation = 10.dp
			) {
				bottomItems.forEach { screen ->
					BottomNavigationItem(
						selected = false,
						onClick = {
							navController.navigate(screen.name)
						},
						icon = {
							Icon(painter = painterResource(id = screen.icon), contentDescription = "${screen.name} page")
						}
					)
				}
			}
		}
	) {
		NavHost(navController = navController, startDestination = Screen.Home.name) {
			composable(Screen.Home.name) { HomeScreen(navController) }
			composable(Screen.Messages.name) { MessagesScreen() }
			composable(Screen.Create.name) { CreateScreen() }
			composable(Screen.Notifications.name) { NotificationsScreen() }
			composable(Screen.Additionally.name) { AdditionallyScreen() }
		}
	}
}