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
	//private lateinit var auth: FirebaseAuth;
	//private val db = Firebase.firestore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ProjectUnionTheme {
				Surface(color = MaterialTheme.colors.background) {
					//auth = Firebase.auth
					
					val navController = rememberNavController()
					NavGraph(navController)
				}
			}
		}
	}
}