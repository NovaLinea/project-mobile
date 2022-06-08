package com.example.projectunion.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuIcons(val title: String, val icon: ImageVector) {
	object Account: MenuIcons("Account", Icons.Default.Person)
	object Search: MenuIcons("Search", Icons.Default.Search)
	object Notifications: MenuIcons("Notifications", Icons.Default.Notifications)
	object Settings: MenuIcons("Settings", Icons.Default.Settings)
	object More: MenuIcons("More", Icons.Default.MoreVert)
}