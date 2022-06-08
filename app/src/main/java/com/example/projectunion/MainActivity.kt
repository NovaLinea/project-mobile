package com.example.projectunion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.navigation.*
import com.example.projectunion.ui.theme.ProjectUnionTheme
import com.google.firebase.database.FirebaseDatabase


class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ProjectUnionTheme {
				//val database = FirebaseDatabase.getInstance()
				//val myRef = database.getReference("projects").child("PROJECT_ID")
				//myRef.setValue("Project 1")

				val navController = rememberNavController()
				NavGraph(navController)
			}
		}
	}
}