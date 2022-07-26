package com.example.novalinea.presentation.screens.home

import androidx.compose.runtime.Composable
import com.example.novalinea.domain.model.ProjectTape
import com.example.novalinea.presentation.navigation.*
import com.example.novalinea.presentation.screens.home.components.CreateBottomSheet
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
				router?.let {
					CreateBottomSheet(it)
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