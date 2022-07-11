package com.example.projectunion.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
		BottomNavRoute.Additionally
	)

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route

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
				navController = navController,
				externalRouter = externalRouter,
				isAuth = isAuth,
				selected = currentRoute == screen.route
			)
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen: BottomNavRoute,
	navController: NavController,
	externalRouter: Router,
	isAuth: Boolean,
	selected: Boolean
) {
	BottomNavigationItem(
		icon = {
			Icon(
				painter = if (selected) painterResource(id = screen.icon_selected) else painterResource(id = screen.icon_default),
				contentDescription = null,
				tint = if (selected) Color.Black else Color.DarkGray
			)
		},
		selected = selected,
		onClick = {
			if (!isAuth && screen.route != BottomNavRoute.Home.route)
				externalRouter.navigateTo(AUTHENTICATION_ROUTE)
			else
				navController.navigate(screen.route)
		}
	)
}