package com.example.projectunion.presentation.navigation.nav_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.projectunion.common.Constants.ARGUMENT_USER_EMAIL_KEY
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.presentation.navigation.MainNavRoute
import com.example.projectunion.presentation.screens.login.LoginScreen
import com.example.projectunion.presentation.screens.register.RegisterScreen
import com.example.projectunion.presentation.screens.veriftyEmail.VerifyEmailScreen

fun NavGraphBuilder.authNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = MainNavRoute.Login.route,
		route = AUTHENTICATION_ROUTE
	) {
		composable(route = MainNavRoute.Login.route) { LoginScreen(navController) }
		composable(route = MainNavRoute.Register.route) { RegisterScreen(navController) }
		composable(
			route = MainNavRoute.VerifyEmail.route
					+ "?${ARGUMENT_USER_EMAIL_KEY}={${ARGUMENT_USER_EMAIL_KEY}}",
			arguments = listOf(
				navArgument(
					name = ARGUMENT_USER_EMAIL_KEY
				) {
					type = NavType.StringType
					defaultValue = "-1"
				}
			)
		) {
			VerifyEmailScreen(
				email = it.arguments?.getString(ARGUMENT_USER_EMAIL_KEY) as String,
				navController = navController
			)
		}
	}
}