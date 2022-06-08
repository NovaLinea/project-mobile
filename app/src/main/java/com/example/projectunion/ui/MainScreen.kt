package com.example.projectunion.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.projectunion.navigation.BottomNavGraph
import com.example.projectunion.navigation.BottomNavRoute
import com.example.projectunion.navigation.Router

@Composable
fun MainScreen(externalRouter: Router) {
	val navController = rememberNavController()

	Scaffold(
		bottomBar = {BottomBar(navController)}
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
fun BottomBar(navController: NavController) {
	val screens = listOf(
		BottomNavRoute.Home,
		BottomNavRoute.Messages
	)

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	BottomNavigation(
		modifier = Modifier.height(50.dp),
		backgroundColor = Color.White,
		elevation = 5.dp
	) {
		screens.forEach { screen ->
			AddItem(
				screen = screen,
				currentDestination = currentDestination,
				navController = navController
			)
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen: BottomNavRoute,
	currentDestination: NavDestination?,
	navController: NavController
) {
	BottomNavigationItem(
		icon = {
			Icon(imageVector = screen.icon, contentDescription = "Navigation icon")
		},
		selected = currentDestination?.hierarchy?.any {
			it.route == screen.route
		} == true,
		onClick = {
			navController.navigate(screen.route)
		}
	)
}