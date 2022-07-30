package com.example.novalinea.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.novalinea.presentation.navigation.NavGraph
import com.example.novalinea.presentation.ui.theme.NovaLineaTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

	private val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

		viewModel.getDataUser()
		installSplashScreen().setKeepOnScreenCondition{
			viewModel.isLoading.value
		}

		lifecycleScope.launchWhenCreated {
			setContent {
				NovaLineaTheme(darkTheme = false) {
					Surface(color = MaterialTheme.colors.background) {
						val systemUiController = rememberSystemUiController()
						//val darkTheme = isSystemInDarkTheme()
						val navController = rememberNavController()

						SideEffect {
							systemUiController.setSystemBarsColor(
								//color = if (darkTheme) Color.White else Color.White
								color = Color.White
							)
						}

						NavGraph(navController)
					}
				}
			}
		}
	}
}