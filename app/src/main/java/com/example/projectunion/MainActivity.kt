package com.example.projectunion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.createRouter
import com.example.projectunion.screens.*
import com.example.projectunion.screens.auth.LoginScreen
import com.example.projectunion.screens.auth.RegisterScreen

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val navController = rememberNavController()

			NavHost(navController = navController, startDestination = ProjectNavRoute.Main.route) {
				composable(ProjectNavRoute.Main.route) {
					MainScreen(
						createRouter { route ->
							navController.navigate(route)
						}
					)
				}
				composable(ProjectNavRoute.Login.route) { LoginScreen() }
				composable(ProjectNavRoute.Register.route) { RegisterScreen() }
				composable(
					route = "${ProjectNavRoute.Project.route}/{projectID}",
					arguments = listOf(
						navArgument("projectID") {
							type = NavType.IntType
						}
					)
				) {
					ProjectScreen(
						navController = navController,
						projectID = it.arguments?.getInt("projectID") ?: -1
					)
				}
				composable(ProjectNavRoute.Create.route) { CreateScreen() }
				composable(ProjectNavRoute.Profile.route) { ProfileScreen() }
				composable(ProjectNavRoute.Favorites.route) { FavoritesScreen() }
				composable(ProjectNavRoute.Settings.route) { SettingsScreen() }
			}
		}
	}
}