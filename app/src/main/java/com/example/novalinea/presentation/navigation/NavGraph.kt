package com.example.novalinea.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.MAIN_ROUTE
import com.example.novalinea.presentation.navigation.nav_graph.authNavGraph
import com.example.novalinea.presentation.navigation.nav_graph.homeNavGraph
import com.example.novalinea.presentation.navigation.nav_graph.messagesNavGraph
import com.example.novalinea.presentation.navigation.nav_graph.profileNavGraph
import com.example.novalinea.presentation.screens.home.components.CreateBottomSheet

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
			CreateBottomSheet(
				externalRouter = createExternalRouter { route, param ->
					navController.navigate(route, param)
				}
			)
		}

		homeNavGraph(navController)
		messagesNavGraph(navController)
		profileNavGraph(navController)
		authNavGraph(navController)
	}
}