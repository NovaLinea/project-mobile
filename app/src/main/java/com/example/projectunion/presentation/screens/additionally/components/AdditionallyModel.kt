package com.example.projectunion.presentation.screens.additionally.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectunion.common.Constants

sealed class AdditionallyModel(val title: String, val icon: ImageVector, val screen_route: String) {
    object Favorites: AdditionallyModel("Избранное", Icons.Default.Bookmark,
        Constants.FAVORITES_SCREEN_ROUTE
    )
    object Subscribes: AdditionallyModel("Подписки", Icons.Default.List,
        Constants.FAVORITES_SCREEN_ROUTE
    )
    object Settings: AdditionallyModel("Настройки", Icons.Default.Settings,
        Constants.SETTINGS_SCREEN_ROUTE
    )
    object Notifications: AdditionallyModel("Уведомления", Icons.Default.Notifications,
        Constants.NOTIFICATIONS_SCREEN_ROUTE
    )
    object Theme: AdditionallyModel("Темы", Icons.Default. WbSunny,
        Constants.NOTIFICATIONS_SCREEN_ROUTE
    )
    object AboutApp: AdditionallyModel("О приложении", Icons.Default.Info,
        Constants.NOTIFICATIONS_SCREEN_ROUTE
    )

}