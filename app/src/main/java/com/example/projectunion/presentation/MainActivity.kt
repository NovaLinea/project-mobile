package com.example.projectunion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.presentation.navigation.*
import com.example.projectunion.presentation.ui.theme.ProjectUnionTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ProjectUnionTheme {
				Surface(color = MaterialTheme.colors.background) {
					val navController = rememberNavController()
					NavGraph(navController)
				}
			}
		}
	}
}