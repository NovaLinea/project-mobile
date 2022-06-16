package com.example.projectunion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectunion.presentation.screens.*

@Composable
fun BottomNavGraph(
	navController: NavHostController,
	externalRouter: Router
) {
	NavHost(
		navController = navController,
		startDestination = BottomNavRoute.Home.route
	) {
		composable(BottomNavRoute.Home.route) { HomeScreen(externalRouter) }
		composable(BottomNavRoute.Messages.route) { MessagesScreen() }
		composable(BottomNavRoute.Profile.route) { ProfileScreen(externalRouter) }
	}
}