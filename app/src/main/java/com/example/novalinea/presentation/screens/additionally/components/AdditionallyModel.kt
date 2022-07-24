package com.example.novalinea.presentation.screens.additionally.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.novalinea.presentation.navigation.ProfileNavRoute

sealed class AdditionallyModel(val title: String, val icon: ImageVector, val screen_route: String) {
    object Theme: AdditionallyModel(
        ProfileNavRoute.Themes.title,
        Icons.Default.WbSunny,
        ProfileNavRoute.Themes.route
    )
    object AboutApp: AdditionallyModel(
        ProfileNavRoute.AboutApp.title,
        Icons.Default.Info,
        ProfileNavRoute.AboutApp.route
    )

}