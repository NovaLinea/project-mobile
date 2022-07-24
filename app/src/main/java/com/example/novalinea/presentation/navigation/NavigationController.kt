package com.example.novalinea.presentation.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationController(
	router: Router? = null,
	startDestination: String,
	screens: List<Pair<String, @Composable (NavController, Router?, Pair<String, Parcelable>?) -> Unit>> = emptyList()
) {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		screens.forEach { screen ->
			composable(screen.first) {
				/*screen.second.invoke(
					navController,
					router,
					//Pair(screen.first, navController.previousBackStackEntry?.savedStateHandle?.get<>(screen.first))
				)*/
			}
		}
	}
}