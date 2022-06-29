package com.example.projectunion.presentation.navigation.nav_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.projectunion.common.Constants.ARGUMENT_CREATE_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_DESCRIPTION_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_ID_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROFILE_NAME_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_PRICE_KEY
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.navigation.createRouter
import com.example.projectunion.presentation.screens.about_app.AboutAppScreen
import com.example.projectunion.presentation.screens.chat.ChatScreen
import com.example.projectunion.presentation.screens.create.CreateScreen
import com.example.projectunion.presentation.screens.edit_profile.EditProfileScreen
import com.example.projectunion.presentation.screens.favorites.FavoritesScreen
import com.example.projectunion.presentation.screens.main.MainScreen
import com.example.projectunion.presentation.screens.notifications.NotificationsScreen
import com.example.projectunion.presentation.screens.profile.ProfileScreen
import com.example.projectunion.presentation.screens.project.ProjectScreen
import com.example.projectunion.presentation.screens.search.SearchScreen
import com.example.projectunion.presentation.screens.settings.SettingsScreen
import com.example.projectunion.presentation.screens.themes.ThemesScreen


fun NavGraphBuilder.mainNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = MainNavRoute.Main.route,
		route = MAIN_ROUTE
	) {
		composable(
			route = MainNavRoute.Main.route
		) {
			MainScreen(
				externalRouter = createRouter { route ->
					navController.navigate(route)
				}
			)
		}

		composable(
			route = MainNavRoute.Profile.route
					+ "?$ARGUMENT_PROFILE_ID_KEY={$ARGUMENT_PROFILE_ID_KEY}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROFILE_ID_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				}
			)
		) {
			ProfileScreen(navController = navController)
		}

		composable(
			route = MainNavRoute.Project.route
					+ "?$ARGUMENT_PROJECT_ID_KEY={$ARGUMENT_PROJECT_ID_KEY}"
					+ "&$ARGUMENT_PROJECT_PRICE_KEY={$ARGUMENT_PROJECT_PRICE_KEY}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROJECT_ID_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_PROJECT_PRICE_KEY
				) {
					type = NavType.IntType
					defaultValue = -1
				}
			)
		) {
			it.arguments?.let { it1 ->
				ProjectScreen(
					price = it1.getInt(ARGUMENT_PROJECT_PRICE_KEY),
					navController = navController
				)
			}
		}

		composable(
			route = MainNavRoute.Create.route
					+ "?$ARGUMENT_CREATE_KEY={$ARGUMENT_CREATE_KEY}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_CREATE_KEY
				) {
					type = NavType.StringType
					defaultValue = "sale"
				}
			)
		) {
			CreateScreen(
				typeProject = it.arguments?.getString(ARGUMENT_CREATE_KEY) as String,
				navController = navController
			)
		}

		composable(
			route = MainNavRoute.EditProfile.route
					+ "?$ARGUMENT_PROFILE_ID_KEY={$ARGUMENT_PROFILE_ID_KEY}"
					+ "&$ARGUMENT_PROFILE_NAME_KEY={$ARGUMENT_PROFILE_NAME_KEY}"
					+ "&$ARGUMENT_PROFILE_DESCRIPTION_KEY={$ARGUMENT_PROFILE_DESCRIPTION_KEY}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROFILE_ID_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_PROFILE_NAME_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_PROFILE_DESCRIPTION_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				}
			)
		) {
			EditProfileScreen(
				id = it.arguments?.getString(ARGUMENT_PROFILE_ID_KEY) as String,
				navController = navController
			)
		}

		composable(route = MainNavRoute.Chat.route) { ChatScreen(navController = navController) }
		composable(route = MainNavRoute.Settings.route) { SettingsScreen() }
		composable(route = MainNavRoute.Search.route) { SearchScreen() }
		composable(route = MainNavRoute.Favorites.route) { FavoritesScreen() }
		composable(route = MainNavRoute.Notifications.route) { NotificationsScreen() }
		composable(route = MainNavRoute.Themes.route) { ThemesScreen() }
		composable(route = MainNavRoute.AboutApp.route) { AboutAppScreen() }
	}
}