package com.example.projectunion.presentation.navigation.nav_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.projectunion.common.Constants.ARGUMENT_CREATE_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_KEY
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.createRouter
import com.example.projectunion.presentation.screens.create.CreateScreen
import com.example.projectunion.presentation.screens.favorites.FavoritesScreen
import com.example.projectunion.presentation.screens.main.MainScreen
import com.example.projectunion.presentation.screens.notifications.NotificationsScreen
import com.example.projectunion.presentation.screens.project.ProjectScreen
import com.example.projectunion.presentation.screens.search.SearchScreen
import com.example.projectunion.presentation.screens.settings.SettingsScreen


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
				projectID = it.arguments?.getString(ARGUMENT_PROJECT_KEY) as String,
				navController
			)
		}

		composable(
			route = MainNavRoute.Create.route,
			arguments = listOf(
				navArgument(ARGUMENT_CREATE_KEY) {
					type = NavType.StringType
				}
			)
		) {
			CreateScreen(
				typeProject = it.arguments?.getString(ARGUMENT_CREATE_KEY) as String,
				navController = navController
			)
		}

		composable(MainNavRoute.Settings.route) { SettingsScreen() }
		composable(MainNavRoute.Search.route) { SearchScreen() }
		composable(MainNavRoute.Favorites.route) { FavoritesScreen() }
		composable(MainNavRoute.Notifications.route) { NotificationsScreen() }
	}
}