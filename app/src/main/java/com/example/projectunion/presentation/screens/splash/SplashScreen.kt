package com.example.projectunion.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projectunion.common.Constants.MAIN_ROUTE
import com.example.projectunion.common.Constants.NAME_APP
import com.example.projectunion.presentation.navigation.MainNavRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
	var startAnimation by remember {
		mutableStateOf(false)
	}
	val alphaAnim = animateFloatAsState(
		targetValue = if (startAnimation) 1f else 0f,
		animationSpec = tween(
			durationMillis = 3000
		)
	)

	LaunchedEffect(key1 = true) {
		startAnimation = true
		delay(2000)
		navController.popBackStack()
		navController.navigate(MainNavRoute.Main.route)
	}
	Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
	Box(
		modifier = Modifier
			.background(
				if (isSystemInDarkTheme()) Color.Black else Color.White
			)
			.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = NAME_APP.toUpperCase(),
			modifier = Modifier.alpha(alpha),
			style = TextStyle(
				color = if (isSystemInDarkTheme()) Color.White else Color.Black,
				fontSize = 22.sp,
				fontWeight = FontWeight.W400
			)
		)
	}
}