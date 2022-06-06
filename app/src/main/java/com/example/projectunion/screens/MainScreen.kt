package com.example.projectunion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.projectunion.R
import com.example.projectunion.navigation.BottomNavRoute
import com.example.projectunion.navigation.Router

@Composable
fun MainScreen( externalRouter: Router) {
	val navController = rememberNavController()

	Scaffold(
		bottomBar = {BottomBar(navController)}
	) {
		innerPadding ->
			Box(
				modifier = Modifier.padding(innerPadding)
			) {
				NavHost(navController = navController, startDestination = BottomNavRoute.Home.route) {
					composable(BottomNavRoute.Home.route) { HomeScreen(externalRouter) }
					composable(BottomNavRoute.Messages.route) { MessagesScreen() }
					composable(BottomNavRoute.Create.route) { CreateScreen() }
					composable(BottomNavRoute.Notifications.route) { NotificationsScreen() }
					composable(BottomNavRoute.Additionally.route) { AdditionallyScreen(externalRouter) }
				}
			}
	}
}

@Composable
fun TopBar(navController: NavController) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	TopAppBar(
		backgroundColor = Color.White,
		elevation = 1.dp
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Text(
				text = stringResource(id = R.string.home_screen),
				style = TextStyle(
					color = Color.Black,
					fontWeight = FontWeight.W600,
					fontSize = 18.sp
				)
			)
		}
	}
}

@Composable
fun BottomBar(navController: NavController) {
	val screens = listOf(
		BottomNavRoute.Home,
		BottomNavRoute.Messages,
		BottomNavRoute.Create,
		BottomNavRoute.Notifications,
		BottomNavRoute.Additionally
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