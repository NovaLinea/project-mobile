package com.example.projectunion.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.login.LoginScreen
import com.example.projectunion.presentation.screens.register.RegisterScreen

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