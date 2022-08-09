package com.example.novalinea.presentation.navigation

import com.example.novalinea.R
import com.example.novalinea.common.Constants.ABOUT_APP_SCREEN_ROUTE
import com.example.novalinea.common.Constants.CHAT_SCREEN_ROUTE
import com.example.novalinea.common.Constants.CREATE_SCREEN_ROUTE
import com.example.novalinea.common.Constants.EDIT_PROFILE_SCREEN_ROUTE
import com.example.novalinea.common.Constants.HOME_SCREEN_ROUTE
import com.example.novalinea.common.Constants.LOGIN_SCREEN_ROUTE
import com.example.novalinea.common.Constants.MESSAGES_SCREEN_ROUTE
import com.example.novalinea.common.Constants.PROFILE_SCREEN_ROUTE
import com.example.novalinea.common.Constants.PROJECT_SCREEN_ROUTE
import com.example.novalinea.common.Constants.REGISTER_SCREEN_ROUTE
import com.example.novalinea.common.Constants.THEMES_SCREEN_ROUTE
import com.example.novalinea.common.Constants.VERIFY_EMAIL_SCREEN_ROUTE
import com.example.novalinea.common.Constants.VIEWING_PHOTO_SCREEN_ROUTE

sealed class BottomNavRoute(val route: String, val icon_default: Int, val icon_selected: Int) {
	object Home: BottomNavRoute(HOME_SCREEN_ROUTE, R.drawable.ic_home_outline, R.drawable.ic_home_fill)
	object Messages: BottomNavRoute(MESSAGES_SCREEN_ROUTE, R.drawable.ic_messages_outline, R.drawable.ic_messages_fill)
	object Profile: BottomNavRoute(PROFILE_SCREEN_ROUTE, R.drawable.ic_person_outline, R.drawable.ic_person_fill)
}

sealed class HomeNavRoute(val route: String) {
	object Project: HomeNavRoute(PROJECT_SCREEN_ROUTE)
	object Create: HomeNavRoute(CREATE_SCREEN_ROUTE)
}

sealed class MessagesNavRoute(val route: String) {
	object Chat: MessagesNavRoute(CHAT_SCREEN_ROUTE)
}

sealed class ProfileNavRoute(val route: String) {
	object ViewingPhoto: ProfileNavRoute(VIEWING_PHOTO_SCREEN_ROUTE)
	object EditProfile: ProfileNavRoute(EDIT_PROFILE_SCREEN_ROUTE)
	object Themes: ProfileNavRoute(THEMES_SCREEN_ROUTE)
	object AboutApp: ProfileNavRoute(ABOUT_APP_SCREEN_ROUTE)
}

sealed class AuthNavRoute(val route: String) {
	object Login: AuthNavRoute(LOGIN_SCREEN_ROUTE)
	object Register: AuthNavRoute(REGISTER_SCREEN_ROUTE)
	object VerifyEmail: AuthNavRoute(VERIFY_EMAIL_SCREEN_ROUTE)
}