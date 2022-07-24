package com.example.novalinea.presentation.screens.main

import android.annotation.SuppressLint
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
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.navigation.nav_graph.BottomNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
	router: Router,
	viewModel: MainViewModel = hiltViewModel()
) {
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route

	val screens = listOf(
		BottomNavRoute.Home,
		BottomNavRoute.Messages,
		BottomNavRoute.Additionally
	)

	Scaffold(
		bottomBar = {
			BottomNavigation(
				modifier = Modifier.height(50.dp),
				backgroundColor = Color.White,
				elevation = 5.dp
			) {
				screens.forEach { screen ->
					AddItem(
						screen = screen,
						navController = navController,
						router = router,
						isAuth = viewModel.isAuth,
						selected = currentRoute == screen.route
					)
				}
			}
		}
	) { innerPadding ->
		Box(
			modifier = Modifier.padding(innerPadding)
		) {
			BottomNavGraph(navController, router)
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen: BottomNavRoute,
	navController: NavController,
	router: Router,
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
				router.routeTo(AUTHENTICATION_ROUTE)
			else
				navController.navigate(screen.route)
		}
	)
}