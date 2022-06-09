package com.example.projectunion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.projectunion.navigation.*
import com.example.projectunion.ui.theme.ProjectUnionTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
	private lateinit var auth: FirebaseAuth;

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			ProjectUnionTheme {
				//val database = FirebaseDatabase.getInstance()
				//val myRef = database.getReference("projects").child("PROJECT_ID")
				//myRef.setValue("Project 1")

				val navController = rememberNavController()
				val db = Firebase.firestore
				auth = Firebase.auth
				NavGraph(auth, db, navController)
			}
		}
	}
}