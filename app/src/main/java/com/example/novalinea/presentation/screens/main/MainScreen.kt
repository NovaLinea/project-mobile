package com.example.novalinea.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.novalinea.common.Constants.ARGUMENT_USER_ID_KEY
import com.example.novalinea.common.Constants.AUTHENTICATION_ROUTE
import com.example.novalinea.common.Constants.USER
import com.example.novalinea.presentation.navigation.BottomNavRoute
import com.example.novalinea.presentation.navigation.Router
import com.example.novalinea.presentation.navigation.nav_graph.BottomNavGraph
import com.example.novalinea.presentation.ui.theme.OpenSans

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
	router: Router,
	showCreateBottomSheet: () -> Unit,
	viewModel: MainViewModel = hiltViewModel()
) {
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	//val profileUserId = navController.currentBackStackEntry?.arguments?.getString(ARGUMENT_USER_ID_KEY)
	val currentRoute = navBackStackEntry?.destination?.route

	val screens = listOf(
		BottomNavRoute.Home,
		BottomNavRoute.Messages,
		BottomNavRoute.Profile
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
						selected = currentRoute == screen.route || currentRoute?.contains(screen.route) == true
					)
				}
			}
		}
	) { innerPadding ->
		Box(
			modifier = Modifier.padding(innerPadding)
		) {
			BottomNavGraph(
				navController = navController,
				router = router,
				showCreateBottomSheet = showCreateBottomSheet
			)
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
				tint = if (selected) Color.Black else Color.Gray
			)
		},
		selected = selected,
		onClick = {
			if (!isAuth && screen.route != BottomNavRoute.Home.route)
				router.routeTo(AUTHENTICATION_ROUTE)
			else if (screen.route == BottomNavRoute.Profile.route) {
				navController.navigate(
					screen.route + "?${ARGUMENT_USER_ID_KEY}=${USER.id}"
				)
			}
			else
				navController.navigate(screen.route)
		}
	)
}