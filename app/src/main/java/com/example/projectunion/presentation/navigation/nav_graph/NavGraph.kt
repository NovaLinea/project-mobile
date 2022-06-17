package com.example.projectunion.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.ROOT_ROUTE

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