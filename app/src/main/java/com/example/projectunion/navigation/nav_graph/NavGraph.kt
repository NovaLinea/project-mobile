package com.example.projectunion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(
	navController: NavHostController
) {
	NavHost(
		navController = navController,
		startDestination = MAIN_ROUTE,
		route = ROOT_ROUTE
	) {
		mainNavGraph(navController = navController)
		authNavGraph(navController = navController)
	}
}