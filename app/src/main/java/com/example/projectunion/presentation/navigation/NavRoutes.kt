package com.example.projectunion.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectunion.common.Constants.ARGUMENT_CREATE_KEY
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_KEY
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.common.Constants.CREATE_SCREEN_ROUTE
import com.example.projectunion.common.Constants.FAVORITES_SCREEN
import com.example.projectunion.common.Constants.FAVORITES_SCREEN_ROUTE
import com.example.projectunion.common.Constants.HOME_SCREEN
import com.example.projectunion.common.Constants.HOME_SCREEN_ROUTE
import com.example.projectunion.common.Constants.LOGIN_SCREEN
import com.example.projectunion.common.Constants.LOGIN_SCREEN_ROUTE
import com.example.projectunion.common.Constants.MAIN_SCREEN
import com.example.projectunion.common.Constants.MAIN_SCREEN_ROUTE
import com.example.projectunion.common.Constants.MESSAGES_SCREEN
import com.example.projectunion.common.Constants.MESSAGES_SCREEN_ROUTE
import com.example.projectunion.common.Constants.NOTIFICATIONS_SCREEN
import com.example.projectunion.common.Constants.NOTIFICATIONS_SCREEN_ROUTE
import com.example.projectunion.common.Constants.PROFILE_SCREEN
import com.example.projectunion.common.Constants.PROFILE_SCREEN_ROUTE
import com.example.projectunion.common.Constants.PROJECT_SCREEN
import com.example.projectunion.common.Constants.PROJECT_SCREEN_ROUTE
import com.example.projectunion.common.Constants.REGISTER_SCREEN
import com.example.projectunion.common.Constants.REGISTER_SCREEN_ROUTE
import com.example.projectunion.common.Constants.SEARCH_SCREEN
import com.example.projectunion.common.Constants.SEARCH_SCREEN_ROUTE
import com.example.projectunion.common.Constants.SETTINGS_SCREEN
import com.example.projectunion.common.Constants.SETTINGS_SCREEN_ROUTE


sealed class MainNavRoute(val route: String, val title: String) {
	object Main: MainNavRoute(MAIN_SCREEN_ROUTE, MAIN_SCREEN)
	object Login: MainNavRoute(LOGIN_SCREEN_ROUTE, LOGIN_SCREEN)
	object Register: MainNavRoute(REGISTER_SCREEN_ROUTE, REGISTER_SCREEN)
	object Project: MainNavRoute("$PROJECT_SCREEN_ROUTE/{$ARGUMENT_PROJECT_KEY}", PROJECT_SCREEN)
	object Create: MainNavRoute("$CREATE_SCREEN_ROUTE/{$ARGUMENT_CREATE_KEY}", CREATE_SCREEN)
	object Settings: MainNavRoute(SETTINGS_SCREEN_ROUTE, SETTINGS_SCREEN)
	object Search: MainNavRoute(SEARCH_SCREEN_ROUTE, SEARCH_SCREEN)
	object Notifications: MainNavRoute(NOTIFICATIONS_SCREEN_ROUTE, NOTIFICATIONS_SCREEN)
	object Favorites: MainNavRoute(FAVORITES_SCREEN_ROUTE, FAVORITES_SCREEN)
}

sealed class BottomNavRoute(val route: String, val title: String, val icon: ImageVector) {
	object Home: BottomNavRoute(HOME_SCREEN_ROUTE, HOME_SCREEN, Icons.Default.Home)
	object Messages: BottomNavRoute(MESSAGES_SCREEN_ROUTE, MESSAGES_SCREEN, Icons.Default.Message)
	object Profile: BottomNavRoute(PROFILE_SCREEN_ROUTE, PROFILE_SCREEN, Icons.Default.Person)

}