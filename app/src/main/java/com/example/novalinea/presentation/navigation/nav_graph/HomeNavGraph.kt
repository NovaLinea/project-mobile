package com.example.novalinea.presentation.navigation.nav_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_TYPE_KEY
import com.example.novalinea.common.Constants.HOME_ROUTE
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.navigation.HomeNavRoute
import com.example.novalinea.presentation.screens.create.CreateScreen
import com.example.novalinea.presentation.screens.project.ProjectScreen

fun NavGraphBuilder.homeNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = HomeNavRoute.Project.route,
		route = HOME_ROUTE
	) {
		composable(HomeNavRoute.Project.route) {
			val project = navController.previousBackStackEntry?.savedStateHandle?.get<ProjectTape>(ARGUMENT_PROJECT_DATA)
			if (project != null) {
				ProjectScreen(
					project = project,
					navController = navController
				)
			}
		}

		/*composable(
			route = HomeNavRoute.Project.route
					+ "?$ARGUMENT_PROJECT_ID_KEY={$ARGUMENT_PROJECT_ID_KEY}"
					+ "&$ARGUMENT_USER_ID_KEY={$ARGUMENT_USER_ID_KEY}"
					+ "&$ARGUMENT_PROJECT_PRICE_KEY={$ARGUMENT_PROJECT_PRICE_KEY}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROJECT_ID_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				},
				navArgument(
					name = ARGUMENT_USER_ID_KEY
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
			it.arguments?.let { args ->
				ProjectScreen(
					projectID = args.getString(ARGUMENT_PROJECT_ID_KEY) as String,
					price = args.getInt(ARGUMENT_PROJECT_PRICE_KEY),
					navController = navController
				)
			}
		}*/

		composable(
			route = HomeNavRoute.Create.route
					+ "?${ARGUMENT_PROJECT_TYPE_KEY}={${ARGUMENT_PROJECT_TYPE_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROJECT_TYPE_KEY
				) {
					type = NavType.StringType
					defaultValue = "sale"
				}
			)
		) {
			CreateScreen(
				typeProject = it.arguments?.getString(ARGUMENT_PROJECT_TYPE_KEY) as String,
				navController = navController
			)
		}
	}
}