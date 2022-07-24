package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.additionally.AdditionallyScreen
import com.example.novalinea.presentation.screens.home.components.HomeCreateBottomSheet
import com.example.novalinea.presentation.screens.messages.MessagesScreen

@Composable
fun BottomNavGraph(
	navController: NavHostController,
	router: Router
) {
	NavHost(
		navController = navController,
		startDestination = BottomNavRoute.Home.route
	) {
		composable(BottomNavRoute.Home.route) { HomeCreateBottomSheet(externalRouter = router) }
		//composable(BottomNavRoute.Home.route) { HomeContainer(externalRouter = router) }
		composable(BottomNavRoute.Messages.route) { MessagesScreen(externalRouter = router) }
		composable(BottomNavRoute.Additionally.route) { AdditionallyScreen(externalRouter = router) }

		/*composable(HomeNavRoute.Project.route) {
			val project = navController.previousBackStackEntry?.savedStateHandle?.get<ProjectTape>(ARGUMENT_PROJECT_DATA)
			if (project != null) {
				ProjectScreen(
					project = project,
					navController = navController
				)
			}
		}*/
	}
}