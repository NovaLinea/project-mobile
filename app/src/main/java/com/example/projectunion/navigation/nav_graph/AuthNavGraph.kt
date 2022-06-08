package com.example.projectunion.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.projectunion.ui.screens.auth.LoginScreen
import com.example.projectunion.ui.screens.auth.RegisterScreen

fun NavGraphBuilder.authNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = MainNavRoute.Login.route,
		route = AUTHENTICATION_ROUTE
	) {
		composable(MainNavRoute.Login.route) { LoginScreen(navController) }
		composable(MainNavRoute.Register.route) { RegisterScreen(navController) }
	}
}