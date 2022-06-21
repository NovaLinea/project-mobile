package com.example.projectunion.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.projectunion.common.Constants.AUTHENTICATION_ROUTE
import com.example.projectunion.presentation.navigation.BottomNavRoute
import com.example.projectunion.presentation.navigation.Router
import com.example.projectunion.presentation.navigation.nav_graph.BottomNavGraph

@Composable
fun MainScreen(
	externalRouter: Router,
	viewModel: MainViewModel = hiltViewModel()
) {
	val navController = rememberNavController()

	Scaffold(
		bottomBar = {BottomBar(navController, externalRouter, viewModel.isAuth)}
	) {
		innerPadding ->
			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				BottomNavGraph(navController, externalRouter)
			}
	}
}

@Composable
fun BottomBar(
	navController: NavController,
	externalRouter: Router,
	isAuth: Boolean
) {
	val screens = listOf(
		BottomNavRoute.Home,
		BottomNavRoute.Messages,
		BottomNavRoute.Profile
	)

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	BottomNavigation(
		modifier = Modifier
			.height(50.dp)
			/*.graphicsLayer {
				shape = RoundedCornerShape(
					topStart = 20.dp,
					topEnd = 20.dp
				)
				clip = true
			}*/,
		backgroundColor = Color.White,
		elevation = 5.dp
	) {
		screens.forEach { screen ->
			AddItem(
				screen = screen,
				currentDestination = currentDestination,
				navController = navController,
				externalRouter = externalRouter,
				isAuth = isAuth
			)
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen: BottomNavRoute,
	currentDestination: NavDestination?,
	navController: NavController,
	externalRouter: Router,
	isAuth: Boolean
) {
	BottomNavigationItem(
		icon = {
			Icon(imageVector = screen.icon, contentDescription = "Navigation icon")
		},
		selected = currentDestination?.hierarchy?.any {
			it.route == screen.route
		} == true,
		onClick = {
			if (!isAuth && screen.route != BottomNavRoute.Home.route)
				externalRouter.navigateTo(AUTHENTICATION_ROUTE)
			else
				navController.navigate(screen.route)
		}
	)
}