package com.example.projectunion.presentation.screens.additionally.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectunion.presentation.navigation.MainNavRoute

sealed class AdditionallyModel(val title: String, val icon: ImageVector, val screen_route: String) {
    object Favorites: AdditionallyModel(
        MainNavRoute.Favorites.title,
        Icons.Default.Bookmark,
        MainNavRoute.Favorites.route
    )
    object Settings: AdditionallyModel(
        MainNavRoute.Settings.title,
        Icons.Default.Settings,
        MainNavRoute.Settings.route
    )
    object Notifications: AdditionallyModel(
        MainNavRoute.Notifications.title,
        Icons.Default.Notifications,
        MainNavRoute.Notifications.route
    )
    object Theme: AdditionallyModel(
        MainNavRoute.Themes.title,
        Icons.Default.WbSunny,
        MainNavRoute.Themes.route
    )
    object AboutApp: AdditionallyModel(
        MainNavRoute.AboutApp.title,
        Icons.Default.Info,
        MainNavRoute.AboutApp.route
    )

}