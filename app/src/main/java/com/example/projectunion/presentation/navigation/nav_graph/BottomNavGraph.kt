package com.example.projectunion.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectunion.presentation.navigation.BottomNavRoute
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.screens.additionally.AdditionallyScreen
import com.example.projectunion.presentation.screens.home.components.HomeCreateBottomSheet
import com.example.projectunion.presentation.screens.messages.MessagesScreen

@Composable
fun BottomNavGraph(
	navController: NavHostController,
	externalRouter: Router
) {
	NavHost(
		navController = navController,
		startDestination = BottomNavRoute.Home.route
	) {
		composable(BottomNavRoute.Home.route) { HomeCreateBottomSheet(externalRouter) }
		composable(BottomNavRoute.Messages.route) { MessagesScreen() }
		composable(BottomNavRoute.Additionally.route) { AdditionallyScreen(externalRouter) }
	}
}