package com.example.projectunion.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = Color.White,
	background = DarkGray,
	onBackground = Color.White,
	surface = LightGray,
	onSurface = DarkGray
)

private val LightColorPalette = lightColors(
	primary = AppBackground,
	primaryVariant = AppBackground,
	secondary = AppBackground
)

@Composable
fun ProjectUnionTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors = if (darkTheme) {
		DarkColorPalette
	} else {
		LightColorPalette
	}

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}