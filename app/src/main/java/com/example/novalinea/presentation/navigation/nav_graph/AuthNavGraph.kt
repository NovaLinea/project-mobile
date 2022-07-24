package com.example.novalinea.presentation.navigation.nav_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.novalinea.common.Constants.ARGUMENT_USER_EMAIL_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.presentation.navigation.AuthNavRoute
import com.example.novalinea.presentation.screens.login.LoginScreen
import com.example.novalinea.presentation.screens.register.RegisterScreen
import com.example.novalinea.presentation.screens.veriftyEmail.VerifyEmailScreen

fun NavGraphBuilder.authNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = AuthNavRoute.Login.route,
		route = AUTHENTICATION_ROUTE
	) {
		composable(route = AuthNavRoute.Login.route) { LoginScreen(navController) }
		composable(route = AuthNavRoute.Register.route) { RegisterScreen(navController) }
		composable(
			route = AuthNavRoute.VerifyEmail.route
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