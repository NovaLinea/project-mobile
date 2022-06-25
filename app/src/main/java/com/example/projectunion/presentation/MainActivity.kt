package com.example.projectunion.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.presentation.navigation.nav_graph.NavGraph
import com.example.projectunion.presentation.ui.theme.ProjectUnionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

		setContent {
			ProjectUnionTheme(darkTheme = false) {
				Surface(color = MaterialTheme.colors.background) {
					val navController = rememberNavController()
					NavGraph(navController)
				}
			}
		}
	}
}