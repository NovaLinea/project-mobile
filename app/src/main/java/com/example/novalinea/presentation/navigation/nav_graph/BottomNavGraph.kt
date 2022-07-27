package com.example.novalinea.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.PresentNested
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.screens.home.HomeScreen
import com.example.novalinea.presentation.screens.messages.MessagesScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavGraph(
	navController: NavHostController,
	router: Router,
	showCreateBottomSheet: () -> Unit
) {
	NavHost(
		navController = navController,
		startDestination = BottomNavRoute.Home.route
	) {
		composable(BottomNavRoute.Home.route) {
			PresentNested{
				HomeScreen(
					router = router,
					showCreateBottomSheet = showCreateBottomSheet
				)
			}
		}
		composable(BottomNavRoute.Messages.route) {
			PresentNested{
				MessagesScreen(externalRouter = router)
			}
		}

		profileNavGraph(navController, router)
	}
}