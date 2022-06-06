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
import com.example.projectunion.navigation.BottomNavRoute
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.Router
import com.example.projectunion.ui.screens.auth.LoginScreen
import com.example.projectunion.ui.screens.auth.RegisterScreen

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
					composable(BottomNavRoute.Additionally.route) { AdditionallyScreen(navController) }

					composable(ProjectNavRoute.Profile.route) { ProfileScreen() }
					composable(ProjectNavRoute.Favorites.route) { FavoritesScreen() }
					composable(ProjectNavRoute.Settings.route) { SettingsScreen() }

					composable(ProjectNavRoute.Login.route) { LoginScreen(navController) }
					composable(ProjectNavRoute.Register.route) { RegisterScreen(navController) }
				}
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