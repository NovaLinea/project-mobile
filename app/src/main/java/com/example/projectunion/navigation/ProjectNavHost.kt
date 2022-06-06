package com.example.projectunion.navigation

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


sealed class ProjectNavRoute(val route: String, title: String) {
	object Main: ProjectNavRoute("main", "Главная")
	object Login: ProjectNavRoute("login", "Вход")
	object Register: ProjectNavRoute("register", "Регистрация")
	object Project: ProjectNavRoute("project", "Проект")
	object Create: ProjectNavRoute("create", "Создать проект")
	object Profile: ProjectNavRoute("profile", "Профиль")
	object Favorites: ProjectNavRoute("favorites", "Избранное")
	object Settings: ProjectNavRoute("settings", "Настройки")
}

sealed class BottomNavRoute(val route: String, title: String, val icon: ImageVector) {
	object Home: BottomNavRoute("home", "Домашняя", Icons.Default.Home)
	object Messages: BottomNavRoute("messages", "Сообщения", Icons.Default.Message)
	object Create: BottomNavRoute("create", "Создание проекта", Icons.Default.Add)
	object Notifications: BottomNavRoute("notifications", "Уведомления", Icons.Default.Notifications)
	object Additionally: BottomNavRoute("additionally", "Дополнительно", Icons.Default.Menu)
}