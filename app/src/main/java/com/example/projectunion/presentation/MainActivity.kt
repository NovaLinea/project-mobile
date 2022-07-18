package com.example.projectunion.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.presentation.navigation.nav_graph.NavGraph
import com.example.projectunion.presentation.ui.theme.ProjectUnionTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen().apply {
			setKeepOnScreenCondition {
				viewModel.isLoading.value
			}
		}
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

		setContent {
			ProjectUnionTheme(darkTheme = false) {
				Surface(color = MaterialTheme.colors.background) {
					val systemUiController = rememberSystemUiController()
					val darkTheme = isSystemInDarkTheme()
					val navController = rememberNavController()

					SideEffect {
						systemUiController.setSystemBarsColor(
							color = if (darkTheme) Color.White else Color.White
						)
					}

					NavGraph(navController)
				}
			}
		}
	}
}