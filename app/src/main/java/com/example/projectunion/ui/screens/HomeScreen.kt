package com.example.projectunion.ui.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectunion.R
import com.example.projectunion.model.Project
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.Router
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(externalRouter: Router) {
	val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
	val currentDate = sdf.format(Date())
	val projects = listOf(
		Project(
			id = 1,
			title = "Title",
			description = "Description",
			price = 15000,
			createdAt = currentDate,
			creatorName = "Vanya Palamarenko"
		)
	)

	Scaffold(
		topBar = {
			TopAppBar(
				backgroundColor = Color.White,
				elevation = 1.dp
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
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
		modifier = Modifier.fillMaxSize()
	) {
		LazyColumn(
			modifier = Modifier
				.background(colorResource(id = R.color.app_background))
				.fillMaxSize()
		) {
			projects.map { item {
				//ProjectItem(it, externalRouter)
			}}
		}
	}
}

@Composable
fun ProjectItem(project: Project) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 0.dp, vertical = 7.dp)
			.clickable {
				//externalRouter.navigateTo("${ProjectNavRoute.Project.route}/${project.id}")
			},
		backgroundColor = Color.White,
		elevation = 0.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 10.dp, vertical = 5.dp),
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = project.creatorName,
					style = TextStyle(
						color = Color.Gray,
						fontSize = 14.sp
					)
				)
				Text(
					text = project.createdAt,
					style = TextStyle(
						color = Color.Gray,
						fontSize = 14.sp
					)
				)
			}
			Row(
				modifier = Modifier
					.padding(top = 10.dp)
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = project.title,
					style = TextStyle(
						color = Color.Black,
						fontWeight = FontWeight.Medium,
						fontSize = 20.sp
					)
				)
				Text(
					text = "${project.price}₽",
					style = TextStyle(
						color = Color.Black,
						fontWeight = FontWeight.Medium,
						fontSize = 20.sp
					)
				)
			}
			Text(
				text = project.description,
				modifier = Modifier
					.padding(top = 5.dp),
				style = TextStyle(
					color = Color.Black,
					fontSize = 15.sp
				)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun prevProjectItem() {
	val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
	val currentDate = sdf.format(Date())
	ProjectItem(project = Project(
		id = 1,
		title = "Title",
		description = "Description",
		price = 15000,
		createdAt = currentDate,
		creatorName = "Vanya Palamarenko"
	))
}