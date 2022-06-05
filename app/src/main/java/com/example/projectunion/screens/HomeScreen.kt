package com.example.projectunion.screens

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectunion.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataProject(val value: String): Parcelable

@Composable
fun HomeScreen(navController: NavController) {
	val projects = listOf(
		DataProject("Project 1"),
		DataProject("Project 2"),
		DataProject("Project 3"),
		DataProject("Project 4"),
		DataProject("Project 5"),
		DataProject("Project 6"),
		DataProject("Project 7"),
		DataProject("Project 8"),
		DataProject("Project 9"),
		DataProject("Project 10"),
		DataProject("Project 11"),
		DataProject("Project 12")
	)

	Scaffold(
		topBar = {
			TopAppBar(
				backgroundColor = Color.White,
				elevation = 1.dp
			) {
				Row(
					modifier = Modifier.padding(start = 10.dp),
					horizontalArrangement = Arrangement.Center
				) {
					Text(
						text = stringResource(id = R.string.home_screen),
						style = TextStyle(
							color = Color.Black,
							fontWeight = FontWeight.W600,
							fontSize = 18.sp
						)
					)
				}
			}
		},
	) {
		LazyColumn(
			modifier = Modifier.background(colorResource(id = R.color.app_backgroung))
		) {
			projects.map { item {
				Card(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 0.dp, vertical = 5.dp)
						.clickable {
							Log.d("AppLog", "Click $it.value")
						},
					backgroundColor = Color.White,
					elevation = 0.dp
				) {
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 50.dp),
					) {
						Text(
							text = it.value,
							style = TextStyle(
								color = Color.Black,
								fontWeight = FontWeight.Medium,
								fontSize = 17.sp
							)
						)
					}
				}
			}}
		}
	}
}