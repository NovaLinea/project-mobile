package com.example.projectunion.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectunion.MainViewModel
import com.example.projectunion.MainViewModelFactory
import com.example.projectunion.R
import com.example.projectunion.model.Project
import com.example.projectunion.navigation.ProjectNavRoute
import com.example.projectunion.navigation.Router

@Composable
fun HomeScreen(externalRouter: Router) {
	val context = LocalContext.current
	val mViewModal: MainViewModel =
		viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
	val projects = listOf(
		Project(id = 1, title = "Title", description = "Description"),
		Project(id = 2, title = "Title", description = "Description"),
		Project(id = 3, title = "Title", description = "Description"),
		Project(id = 4, title = "Title", description = "Description"),
		Project(id = 5, title = "Title", description = "Description"),
		Project(id = 6, title = "Title", description = "Description"),
		Project(id = 7, title = "Title", description = "Description"),
		Project(id = 8, title = "Title", description = "Description"),
		Project(id = 9, title = "Title", description = "Description"),
		Project(id = 10, title = "Title", description = "Description"),
		Project(id = 11, title = "Title", description = "Description"),
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
				ProjectItem(it, externalRouter)
			}}
		}
	}
}

@Composable
fun ProjectItem(project: Project, externalRouter: Router) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 0.dp, vertical = 7.dp)
			.clickable {
				externalRouter.navigateTo("${ProjectNavRoute.Project.route}/${project.id}")
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
				text = project.title,
				style = TextStyle(
					color = Color.Black,
					fontWeight = FontWeight.Medium,
					fontSize = 17.sp
				)
			)
		}
	}
}