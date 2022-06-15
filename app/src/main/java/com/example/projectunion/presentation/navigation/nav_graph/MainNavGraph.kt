package com.example.projectunion.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_KEY
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.presentation.screens.*


fun NavGraphBuilder.mainNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = MainNavRoute.Main.route,
		route = MAIN_ROUTE
	) {
		composable(MainNavRoute.Main.route) {
			MainScreen(
				createRouter { route ->
					navController.navigate(route)
				}
			)
		}
		composable(
			route = MainNavRoute.Project.route,
			arguments = listOf(
				navArgument(ARGUMENT_PROJECT_KEY) {
					type = NavType.StringType
				}
			)
		) {
			ProjectScreen(
				projectID = it.arguments?.getInt(ARGUMENT_PROJECT_KEY) ?: -1,
				navController
			)
		}

		composable(MainNavRoute.Create.route) { CreateScreen() }
		composable(MainNavRoute.Settings.route) { SettingsScreen() }
		composable(MainNavRoute.Search.route) { SearchScreen() }
		composable(MainNavRoute.Favorites.route) { FavoritesScreen() }
		composable(MainNavRoute.Notifications.route) { NotificationsScreen() }
	}
}