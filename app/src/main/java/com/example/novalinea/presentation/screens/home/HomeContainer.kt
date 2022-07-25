package com.example.novalinea.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.novalinea.common.Constants.ARGUMENT_PROJECT_DATA
import com.example.novalinea.common.Constants.TAG
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.home.components.HomeCreateBottomSheet
import com.example.novalinea.presentation.screens.project.ProjectScreen

@Composable
fun HomeContainer(
	externalRouter: Router
) {
	NavigationController(
		startDestination = BottomNavRoute.Home.route,
		router = externalRouter,
		screens = listOf(
			Pair(BottomNavRoute.Home.route) { nav, router, _ ->
				router?.let {
					HomeCreateBottomSheet(nav, it)
				}
			},
			Pair(HomeNavRoute.Project.route) { nav, _, param ->
				//param?.getParcelable<ProjectTape>(ARGUMENT_PROJECT_DATA)?.let {
				ProjectScreen(
					project = param?.second as ProjectTape,
					navController = nav
				)
			}
		)
	)
}