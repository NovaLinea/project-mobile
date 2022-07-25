package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_ID_KEY
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_TYPE_KEY
import com.example.novalinea.common.Constants.HOME_ROUTE
import com.example.novalinea.common.Constants.TYPE_PROJECT_SALE
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.navigation.HomeNavRoute
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.screens.create.CreateScreen
import com.example.novalinea.presentation.screens.project.ProjectScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = HomeNavRoute.Project.route,
		route = HOME_ROUTE
	) {
		composable(
			route = HomeNavRoute.Project.route
					+ "?${ARGUMENT_PROJECT_ID_KEY}={${ARGUMENT_PROJECT_ID_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROJECT_ID_KEY
				) {
					type = NavType.StringType
				}
			)
		) {
			val project = navController.previousBackStackEntry?.savedStateHandle?.get<ProjectTape>(ARGUMENT_PROJECT_DATA)
			project?.let {
				PresentNested {
					ProjectScreen(
						project = it,
						navController = navController
					)
				}
			}
		}

		composable(
			route = HomeNavRoute.Create.route
					+ "?${ARGUMENT_PROJECT_TYPE_KEY}={${ARGUMENT_PROJECT_TYPE_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_PROJECT_TYPE_KEY
				) {
					type = NavType.StringType
					defaultValue = TYPE_PROJECT_SALE
				}
			)
		) {
			PresentNested {
				CreateScreen(
					typeProject = it.arguments?.getString(ARGUMENT_PROJECT_TYPE_KEY) as String,
					navController = navController
				)
			}
		}
	}
}