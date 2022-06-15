package com.example.projectunion.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectunion.common.Constants.ARGUMENT_PROJECT_KEY


sealed class MainNavRoute(val route: String, val title: String) {
	object Main: MainNavRoute("main_screen", "Главная")
	object Login: MainNavRoute("login_screen", "Вход")
	object Register: MainNavRoute("register_screen", "Регистрация")
	object Project: MainNavRoute("project_screen/{$ARGUMENT_PROJECT_KEY}", "Проект")
	object Create: MainNavRoute("create_screen", "Создать проект")
	object Profile: MainNavRoute("profile_screen", "Профиль")
	object Settings: MainNavRoute("settings_screen", "Настройки")
	object Search: MainNavRoute("search_screen", "Поиск")
	object Notifications: MainNavRoute("notifications_screen", "Уведомления")
	object Favorites: MainNavRoute("favorites_screen", "Избранное")
}

sealed class BottomNavRoute(val route: String, val title: String, val icon: ImageVector) {
	object Home: BottomNavRoute("home_screen", "Домашняя", Icons.Default.Home)
	object Messages: BottomNavRoute("messages_screen", "Сообщения", Icons.Default.Message)
}