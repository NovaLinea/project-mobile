package com.example.projectunion.presentation.navigation

import com.example.projectunion.R
import com.example.projectunion.common.Constants.ABOUT_APP_SCREEN
import com.example.projectunion.common.Constants.ABOUT_APP_SCREEN_ROUTE
import com.example.projectunion.common.Constants.ADDITIONALLY_SCREEN
import com.example.projectunion.common.Constants.ADDITIONALLY_SCREEN_ROUTE
import com.example.projectunion.common.Constants.CHAT_SCREEN
import com.example.projectunion.common.Constants.CHAT_SCREEN_ROUTE
import com.example.projectunion.common.Constants.CREATE_SCREEN
import com.example.projectunion.common.Constants.CREATE_SCREEN_ROUTE
import com.example.projectunion.common.Constants.EDIT_PROFILE_SCREEN
import com.example.projectunion.common.Constants.EDIT_PROFILE_SCREEN_ROUTE
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
import com.example.projectunion.common.Constants.THEMES_SCREEN
import com.example.projectunion.common.Constants.THEMES_SCREEN_ROUTE


sealed class MainNavRoute(val route: String, val title: String) {
	object Main: MainNavRoute(MAIN_SCREEN_ROUTE, MAIN_SCREEN)
	object Login: MainNavRoute(LOGIN_SCREEN_ROUTE, LOGIN_SCREEN)
	object Register: MainNavRoute(REGISTER_SCREEN_ROUTE, REGISTER_SCREEN)
	object Project: MainNavRoute(PROJECT_SCREEN_ROUTE, PROJECT_SCREEN)
	object Create: MainNavRoute(CREATE_SCREEN_ROUTE, CREATE_SCREEN)
	object Settings: MainNavRoute(SETTINGS_SCREEN_ROUTE, SETTINGS_SCREEN)
	object Search: MainNavRoute(SEARCH_SCREEN_ROUTE, SEARCH_SCREEN)
	object Notifications: MainNavRoute(NOTIFICATIONS_SCREEN_ROUTE, NOTIFICATIONS_SCREEN)
	object Favorites: MainNavRoute(FAVORITES_SCREEN_ROUTE, FAVORITES_SCREEN)
	object Profile: MainNavRoute(PROFILE_SCREEN_ROUTE, PROFILE_SCREEN)
	object EditProfile: MainNavRoute(EDIT_PROFILE_SCREEN_ROUTE, EDIT_PROFILE_SCREEN)
	object Themes: MainNavRoute(THEMES_SCREEN_ROUTE, THEMES_SCREEN)
	object AboutApp: MainNavRoute(ABOUT_APP_SCREEN_ROUTE, ABOUT_APP_SCREEN)
	object Chat: MainNavRoute(CHAT_SCREEN_ROUTE, CHAT_SCREEN)
}

sealed class BottomNavRoute(val route: String, val title: String, val icon_default: Int, val icon_selected: Int) {
	object Home: BottomNavRoute(HOME_SCREEN_ROUTE, HOME_SCREEN, R.drawable.ic_home_outline, R.drawable.ic_home_fill)
	object Messages: BottomNavRoute(MESSAGES_SCREEN_ROUTE, MESSAGES_SCREEN, R.drawable.ic_messages_outline, R.drawable.ic_messages_fill)
	object Additionally: BottomNavRoute(ADDITIONALLY_SCREEN_ROUTE, ADDITIONALLY_SCREEN, R.drawable.ic_more_horiz, R.drawable.ic_more_horiz)
}