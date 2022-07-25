package com.example.novalinea.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.presentation.navigation.nav_graph.authNavGraph
import com.example.novalinea.presentation.navigation.nav_graph.homeNavGraph
import com.example.novalinea.presentation.navigation.nav_graph.messagesNavGraph
import com.example.novalinea.presentation.screens.main.MainScreen
import com.example.novalinea.presentation.screens.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
	navController: NavHostController
) {
	NavHost(
		navController = navController,
		startDestination = MAIN_ROUTE
	) {
		composable(
			route = MAIN_ROUTE
		) {
			MainScreen(
				router = createExternalRouter { route, param ->
					navController.navigate(route, param)
				}
			)
		}

		composable(
			route = BottomNavRoute.Profile.route
					+ "?${ARGUMENT_USER_ID_KEY}={${ARGUMENT_USER_ID_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_USER_ID_KEY
				) {
					type = NavType.StringType
				}
			)
		) {
			PresentNested{
				ProfileScreen(
					userID = it.arguments?.getString(ARGUMENT_USER_ID_KEY) as String,
					navController = navController
				)
			}
		}

		homeNavGraph(navController)
		messagesNavGraph(navController)
		authNavGraph(navController)
	}
}