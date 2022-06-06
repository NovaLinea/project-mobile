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
import com.example.projectunion.ui.screens.*
import com.example.projectunion.ui.theme.ProjectUnionTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ProjectUnionTheme {
				val navController = rememberNavController()

				NavHost(navController = navController, startDestination = ProjectNavRoute.Main.route) {
					composable(ProjectNavRoute.Main.route) {
						MainScreen(
							createRouter { route ->
								navController.navigate(route)
							}
						)
					}
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
				}
			}
		}
	}
}