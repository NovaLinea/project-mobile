package com.example.projectunion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.navigation.Screen
import com.example.projectunion.screens.*

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val navController = rememberNavController()

			NavHost(navController = navController, startDestination = Screen.Main.name) {
				composable(Screen.Main.name) { MainScreen() }
				composable(Screen.Project.name) { ProjectScreen()}
			}
		}
	}
}