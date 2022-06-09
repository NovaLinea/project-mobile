package com.example.projectunion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavGraph(
	auth: FirebaseAuth,
	db: FirebaseFirestore,
	navController: NavHostController
) {
	NavHost(
		navController = navController,
		startDestination = MAIN_ROUTE,
		route = ROOT_ROUTE
	) {
		mainNavGraph(navController = navController)
		authNavGraph(auth = auth, db = db, navController = navController)
	}
}