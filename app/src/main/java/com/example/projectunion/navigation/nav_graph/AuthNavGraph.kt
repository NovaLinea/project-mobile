package com.example.projectunion.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.projectunion.ui.screens.auth.LoginScreen
import com.example.projectunion.ui.screens.auth.RegisterScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun NavGraphBuilder.authNavGraph(
	auth: FirebaseAuth,
	db: FirebaseFirestore,
	navController: NavHostController
) {
	navigation(
		startDestination = MainNavRoute.Login.route,
		route = AUTHENTICATION_ROUTE
	) {
		composable(MainNavRoute.Login.route) { LoginScreen(auth, navController) }
		composable(MainNavRoute.Register.route) { RegisterScreen(auth, db, navController) }
	}
}