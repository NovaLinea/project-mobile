package com.example.novalinea.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.project.ProjectScreen

@Composable
fun HomeContainer(
	externalRouter: Router
) {
	NavigationController(
		startDestination = BottomNavRoute.Home.route,
		router = externalRouter,
		screens = listOf(
			Pair(BottomNavRoute.Home.route) { _, router, _ ->
				HomeScreen(router, {})
			},
			Pair(HomeNavRoute.Project.route) { nav, _, params ->
				Log.d(TAG, "Container " + params.toString())
				params?.getParcelable<ProjectTape>(ARGUMENT_PROJECT_DATA)?.let {
					ProjectScreen(
						project = it,
						navController = nav
					)
				}
			}
		)
	)
}