package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.Router
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
		composable(BottomNavRoute.Home.route) { HomeCreateBottomSheet(navController = navController, externalRouter = router) }
		composable(BottomNavRoute.Messages.route) { MessagesScreen(externalRouter = router) }
		//composable(BottomNavRoute.Additionally.route) { AdditionallyScreen(externalRouter = router) }
		profileNavGraph(navController, router)
	}
}